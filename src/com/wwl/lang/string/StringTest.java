package com.wwl.lang.string;

public class StringTest {
	public static void main(String[] args) {
		//1、转大写 :toUpperCase()
		System.out.println("abcdefghijklmnopqrstuvwsyz".toUpperCase());
		
		//2、转小写：toLowerCase()
		System.out.println("ABCDEFGHIJKLMNOPQRSTUVWSYZ".toLowerCase());
		
		//3、获取指定的内容字在符串中的第一个出现的索引
		System.out.println("第一个'X'和第二个'X'在本字符串的第几个索引".indexOf("X")); //打印第四个
		
		//4、获取指定的内容字在符串最后出现的索引
		System.out.println("第一个'X'和第二个'X'在本字符串的第几个索引".lastIndexOf("X")); //打印第11个''代表一个索引
		
		//5、截取
		String a="中国人最牛逼";
		System.out.println(a.substring(5));//打印：逼  注意：从0开始算
		System.out.println(a.substring(0,3));//打印 ：中国人
		//-------------------配合获取索引进行截取----------------------------------------
		int a1=a.indexOf("中");//打印：0
		int a2=a.indexOf("人");//打印：2
		System.out.println(a.substring(a1, a2+1));//打印：中国人  (包头不包尾)
		
		//6、通过指定内容把字符串截成字符串数组
		String b="你是哪位呢,w我也是啊,笑死人了,呵呵，鸟你,啊"; //注意中文的逗号和英文的逗号是不一样的
		String[] b1=b.split(",");//[你是哪位呢, w我也是啊, 笑死人了, 呵呵，鸟你, 啊] 分成了5组
		String[] b2=b.split(",", 2);//只截取前两组其它不截取
		
		//7、判断字符串是否为空 配合trim好使
		System.out.println("         ".trim().isEmpty());//打印true
		
		//8、判断后缀：是为true 否则false
		String ends="A.java";
		System.out.println(ends.endsWith("java"));
	}
}
