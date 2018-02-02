package com.go.library;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by go on 2018/2/1.
 */


@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface AutoParcel {
}
