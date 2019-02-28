package com.wwl.porxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class B<T> {
	private T t;
	private String methodName;
	public B(){}
	
	public B(Class<?> t,String methodName ) throws InstantiationException, IllegalAccessException {
		this.t=(T) t.newInstance();
		this.methodName=methodName;
	}
	//创建一个方法
	public T method(){
		return (T) Proxy.newProxyInstance(t.getClass().getClassLoader(), t.getClass().getInterfaces(), new InvocationHandler() {
			
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				if(methodName.equals(method.getName())){
					System.out.println("在加载此方法前加入代码逻辑");
					T c=(T) method.invoke(t, args);
					System.out.println("在方法结束后加入代码逻辑");
					return c;
				}
				return	 method.invoke(t, args);
			}
		});
	}
	  


	
	
}
