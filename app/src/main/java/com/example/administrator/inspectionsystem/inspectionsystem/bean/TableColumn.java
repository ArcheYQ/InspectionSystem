package com.example.administrator.inspectionsystem.inspectionsystem.bean;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface TableColumn {
    String field();
    boolean autoIncrease() default false;
    boolean primary() default false;
}
