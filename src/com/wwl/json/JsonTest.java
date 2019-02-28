package com.wwl.json;

import net.sf.json.JSONObject;

public class JsonTest {
	public static void main(String[] args) {
		JSONObject  j = new JSONObject(); //大括号 {} 的对象
		j.put("1", "第一");
		j.put("2", "第二");
		j.put("3", "第三");
		JSONObject  ja = new JSONObject();//中括号 [] 的对象
		ja.put("is","第一");
		ja.put("is2","第二");
		ja.put("is3","第三");
		//把第二个JSONObject对象 put到 j对象中
		j.put("数组", ja);
		//查询操作：只能查询，j中key，不能查询ja对象中的key ，没有查出为 null
		System.out.println(j.get("3"));
		//删除掉 key 为‘3’的数据
		j.remove("3");
		System.out.println(j.toString());
	}
}
