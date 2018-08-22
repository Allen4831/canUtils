package com.can.bindview;

import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
/**
 * Created by CAN on 18-8-22.
 */
final class ClassValidator {
    static boolean isPrivate(Element element){
        return element.getModifiers().contains(Modifier.PRIVATE);
    }
    static String getClassName(TypeElement element,String packageName){
        int packageLen = packageName.length()+1;
        return element.getQualifiedName().toString().substring(packageLen).replace('.','$');
    }
}
