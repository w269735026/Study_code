package com.wwl.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ArrayListTest {
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		//添加
		list.add("增加一个字符串");
		
		//取值
		//String a=list[0] 不可以这样做，C#就推荐这样赋值和取值
		String a=list.get(0);
		
		//修改
		list.set(0, "把你修改了");
		a=list.get(0);
		System.out.println(a);
		
		//删除
		list.add(1, "呵呵");
		//list.remove("呵呵");//通过值删除
		list.remove(0);    //通过索引删除
		a=list.get(0);     //没有索引就报错
		System.out.println(a); //打印：呵呵 ，删除了第一个，会把第二个移到第一位
		
		//转成数组
		Object[] aa=list.toArray();  //强转字符串数组也会报错不知道为什么一定要Object接收
		System.out.println(Arrays.toString(aa));
		
		//迭代遍历list集合
		Iterator<String> iter=list.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
	}
}
