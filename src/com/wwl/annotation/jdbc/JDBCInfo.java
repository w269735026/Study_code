package com.wwl.annotation.jdbc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
//注解本质就是一个接口,接口中可以有常量和抽象方法，抽象方法在注解中就称之为注解属性
//注解属性类型:①基本类型②String③Class④Annotation⑤Enum:枚举⑥最后是1到5的数组
//@Retention  规定注解保留到什么阶段:值为RetentionPolicy的三个枚举值
//SOURCE:只在代码中保留,在字节码文件中就删除了
//CLASS:在代码和字节码文件中保留
//RUNTIME:所有阶段都保留
@Retention(RetentionPolicy.RUNTIME)
//@Target 规定注解作用在什么上面 	值为ElementType的枚举值
//TYPE:作用在类 接口 等上面
//METHOD:作用方法上面
//FIELD:作用字段上面
@Target(ElementType.METHOD) //设置为只能作用在方法上
public @interface JDBCInfo {
	
	//定义4个基本的连接mysql数据库属性
	String driverClass() default "com.mysql.jdbc.Driver";
	String url() default "jdbc:mysql://localhost:3306/wwl";
	String user() default "root";
	String password() default "123456";
}
