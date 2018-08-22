package com.can.bindview;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

/**
 * Created by CAN on 18-8-22.
 */

public class BindViewProcessor extends AbstractProcessor {

    private Filer mFiler;
    private Messager mMessager;
    private Elements mElements;

    private static final String LOG = "BindViewProcessor";

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mFiler = processingEnvironment.getFiler();
        mElements = processingEnvironment.getElementUtils();
        mMessager = processingEnvironment.getMessager();
//        mMessager.printMessage(Diagnostic.Kind.ERROR, "BindViewProcessor.init");
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotationTypes = new LinkedHashSet<>();
        annotationTypes.add(BindView.class.getCanonicalName());
        return annotationTypes;
    }


    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }



    private Map<String,ProxyInfo> mProxyMap = new HashMap<>();
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
//        mMessager.printMessage(Diagnostic.Kind.ERROR, "BindViewProcessor.process");
        mProxyMap.clear();
        //遍历所有被BindView过的元素
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(BindView.class);
        //收集信息
        for(Element element : elements){
            if(!checkAnnotationValid(element,BindView.class)){
                return true;
            }
            VariableElement variableElement = (VariableElement) element;
            TypeElement typeElement = (TypeElement) variableElement.getEnclosingElement();
            String qualifiedName = typeElement.getQualifiedName().toString();
            ProxyInfo proxyInfo = mProxyMap.get(qualifiedName);
            if(proxyInfo==null){
                proxyInfo = new ProxyInfo(mElements,typeElement);
                mProxyMap.put(qualifiedName,proxyInfo);
            }
            BindView bindView = variableElement.getAnnotation(BindView.class);
            int id = bindView.value();
            proxyInfo.variables.put(id,variableElement);
        }


        for(String key:mProxyMap.keySet()){
            ProxyInfo proxyInfo = mProxyMap.get(key);
            try {
                JavaFileObject javaFileObject = processingEnv.getFiler().createSourceFile(proxyInfo.getProxyClassFullName(),proxyInfo.getTypeElement());
                Writer writer = javaFileObject.openWriter();
                writer.write(proxyInfo.generateJavaCode());
                writer.flush();
                writer.close();
            } catch (IOException e) {
                error(proxyInfo.getTypeElement(),"Unable to write compile for type %s: %s",proxyInfo.getTypeElement(),e.getMessage());
            }
        }
        return false;
    }

    private boolean checkAnnotationValid(Element element,Class clazz){
        if(element.getKind()!= ElementKind.FIELD){
            error(element,"%s must be decalared on field.",clazz.getSimpleName());
            return false;
        }
        if (ClassValidator.isPrivate(element)) {
            error(element, "%s() must can not be private.", element.getSimpleName());
            return false;
        }
        return true;
    }

    private void error(Element element,String message,Object...args){
        if(args.length>0){
            message = java.lang.String.format(message,args);
        }
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE,message,element);
    }
}
