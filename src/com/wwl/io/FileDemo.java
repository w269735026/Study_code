package com.wwl.io;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

public class FileDemo {
	public static void main(String[] args) throws IOException {
		listFile2();
	}
	//小demo：在d盘根目录创建a.txt文件：使用构造方法带两个参数操作(父路径可以先做预处理动作：如先删除d盘没用的)
	public void demo() throws IOException {
		File file = new File("d:\\");
		File file2 = new File(file,"a.txt");
		if(!file2.exists()) {
			file2.createNewFile();
		}
	}
	
	//路径问题:打印相对路径
	public static void path() {
		//在windows机器上的目录分隔符是 \  ,在linux机器上的目录分隔符是/
		System.out.println("目录分隔符："+ File.separator);
		File file = new  File(".");
		System.out.println("当前路径是："+ file.getAbsolutePath());
	}
	//重命名文件名:也可以重命名文件夹名
	public static void name() {
		File file = new  File("d:\\a.txt");
		File file2 = new  File("d:\\cccc");
		file.renameTo(file2);//要注意需要被重命名的方法进行调用方法
	}
	//移动文件夹
	public static void name2() {
		File file = new  File("d:\\a");
		File file2 = new  File("e:\\a");
		boolean b = file.renameTo(file2);//要注意需要被重命名的方法进行调用方法
		System.out.println(b);
	}
	
	public static void demo2() {
		File file = new File("d:\\a.txt");
		System.out.println("存在吗？"+ file.exists());
		System.out.println("判断是否是一个文件："+file.isFile()); //如果是文件返回true，否则返回false.
		System.out.println("判断是否是一个文件夹："+ file.isDirectory()); // 是文件夹返回ture，否则返回false.
		System.out.println("是隐藏的 文件吗："+ file.isHidden());
		System.out.println("是绝对路吗？"+ file.isAbsolute());
	}
	//需求1 ：  指定一个文件夹，然后该文件夹下面所有java文件。
	public static void demo3() {
		File file = new File("d:\\");
		File[] files = file.listFiles();
		for (File f : files) {
			if(f.getName().equals("a")) {
				File[] listFiles = f.listFiles();
				for (File f2 : listFiles) {
					if(f2.getName().endsWith("java")) {
						System.out.println(f2.getName());
					}
				}
			}
		}
	}
	/*需求2： 指定一个文件夹，然后列出文件夹下面的所有子文件与文件夹，但是格式要如下:
		 
		文件：
			文件名1
			文件名2
			文件名3
			..

		文件夹：
			文件夹名1
			文件夹名2
			文件夹名3
			....*/
	public static void listFile(){
		File dir = new File("d:\\");
		File[] files = dir.listFiles();//获取到所有的子文件
		System.out.println("文件：");
		for(File fileItem : files){
			if(fileItem.isFile()){
				System.out.println("\t"+fileItem.getName());
			}
		}
		System.out.println("文件夹：");
		for(File fileItem : files){
			if(fileItem.isDirectory()){
				System.out.println("\t"+fileItem.getName());
			}
		}
	}
	//返回指定当前目录中符合过滤条件的子文件或子目录。对于文件这样操作会返回null。
	public static void listFile2(){
		File dir = new File("d:\\a");
		File[] files = dir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				//System.out.println("文件夹:"+dir+" 文件名："+ name);
				return name.endsWith(".java");
			}
		});
		for(File file : files){
			System.out.println(file.getName());
		}
	}
}
