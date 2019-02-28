package com.wwl.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
//第一步 通过反射获取目标类的字节码
		Class<A> clazz1=A.class;//第一种方式 
		//Class<Reflect> clazz2=(Class<Reflect>) new Reflect().getClass();//第二种方式 
		Class clazz=Class.forName("com.wwl.reflect.A");
		
//第二步 通过字节码对象可以：1无参实例化(创建对象) 2获得：类的构造方法象(Constructors) 3 获得：类的方法对象（method） 4获得：类的属性对象(field)
		
//1、无参实例化对象操作
		/*Reflect r=(Reflect) clazz.newInstance();//经测试目标类么有无参构造方法会报错
		r.a(4);*/
//------------------------------------------------------------------------------------------------------------------------------------------		
		
//2、获取构造对象(Constructors)进行实例调用A类中的方法b(String name)
		A r=null;
		int i1=1;
		Constructor[] con=clazz.getDeclaredConstructors(); //获取只有本类的构造方法
		for(Constructor c :con){
			System.out.println(c);                   //打印：构造方法的全称：public com.wwl.reflect.Reflect(java.lang.Integer,java.lang.String)
			if(i1==1){
				r=(A) c.newInstance(3,"你好");       //进行有参构造方法实例
				i1=2;
			}
			
		}
		r.b("hehe");
		System.out.println("--------------------------------------------------");
//------------------------------------------------------------------------------------------------------------------------------------------		
//3、获取类中	方法对象（method）
	//执行A类中b()方法	
		Method[] method=clazz.getMethods();                                       //获取本类中所有的方法
		for(Method m:method){
			System.out.println(m);                                                //打印方法名
			
			//截取方法名：如：b(java.lang.String),获取方法名：b
			String methodName=m.toString();
			int index=methodName.indexOf("(");
			String indexName=methodName.substring(0,index);
			int index1=indexName.lastIndexOf(".");
			String resultName=methodName.substring(index1+1, indexName.length());
			
			//截取参数:如：b(java.lang.String,java.lang.Integer),获取参数：[java.lang.String,java.lang.Integer]
			String paramName=methodName.substring(index+1,methodName.length()-1);
			String[] paramN=paramName.split(",");                            
			
			if(resultName.equals("b")){
				for(int i=0;i<paramN.length;i++){
					
					if(paramN.length==1){ //注意：数组空字符串也有长度是1       判断只有1个参数进入
						if("".equals(paramN[i])){
							m.invoke(r);      //参数是对象
						}
						if("java.lang.String".equals(paramN[i])){
							m.invoke(r,"参数是字符串"); 
						}
						if("java.lang.Integer".equals(paramN[i])){
							m.invoke(r,1); 
						}
					}
				}                                                    
			}
		}
	}
}
