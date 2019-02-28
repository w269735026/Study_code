package com.wwl.annotation.getName;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.wwl.annotation.getName.pojo.GetNameValue;

/**
 * 简单测试：通过处理此注解，让标记了此注解的方法获取注解中的值
 * @author wenwenliang
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface GetName {
	String value() default"默认值";
	int id() default 1;
	String name() default "测试";
	Class clazz() default GetNameValue.class;
}
