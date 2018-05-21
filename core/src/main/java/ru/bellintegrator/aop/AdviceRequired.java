package ru.bellintegrator.aop;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Аннотация для указания применения совета
 * Created by YANesterov on 21.05.2018.
 */
@Retention(RUNTIME)
@Target({TYPE, METHOD})
public @interface AdviceRequired {
}
