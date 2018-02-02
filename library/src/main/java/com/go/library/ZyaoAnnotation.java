package com.go.library;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by go on 2018/2/2.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
public @interface ZyaoAnnotation
{
    String name() default "undefined";

    String text() default "";
}

