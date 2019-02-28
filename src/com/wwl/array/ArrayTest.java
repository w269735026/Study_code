package com.wwl.array;

import java.util.Arrays;//工具类

public class ArrayTest {
	public static void main(String[] args) {
		
		Integer[] array=new Integer[5];
		array[0]=0;
		array[2]=1;
		array[1]=2;
		array[3]=3;
		array[4]=4;
		
		//排序操作从大到小排序
		System.out.println(Arrays.toString(array));
		for(int i=0;i<array.length;i++){
			for(int j=i+1;j<array.length;j++){
				if(array[i]<array[j]){
					Integer item=array[i];
					array[i]=array[j];
					array[j]=item;
				}
			}
			
		}
		System.out.println(Arrays.toString(array));
	/***********************************************************/
	//二分法(前提需要先排序)：取中间的值进行对比	
		
	}
}
