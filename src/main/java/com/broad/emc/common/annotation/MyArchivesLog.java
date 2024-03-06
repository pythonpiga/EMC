package com.broad.emc.common.annotation;


import java.lang.annotation.*;


/**
 * 自定义注解 日志注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface MyArchivesLog {

    /**
     * 操作说明
     */
    public String operteContent() default "";

}

