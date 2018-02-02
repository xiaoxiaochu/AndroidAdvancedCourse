package com.go.android.course.anntation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by go on 2018/2/1.
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SQLString {
    //定义了元素名为value，在符合条件时，我们使用时，可以直接在括号内输入value的值就ok了。
    int value() default 0;
    String name() default "";
    Constraints constraints() default @Constraints;
}
