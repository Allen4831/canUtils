package com.can.bindview;


import com.google.auto.service.AutoService;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;

/**
 * Created by CAN on 18-8-21.
 * auto-service库可以帮我们去生成META-INF等信息
 */
@AutoService(Processor.class)
public class BindViewProcessor extends AbstractProcessor{



    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        return false;
    }



}
