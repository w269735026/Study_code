package com.wwl.socket.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 * * 1.发送Send
 * 创建DatagramSocket, 随机端口号
 * 创建DatagramPacket, 指定数据, 长度, 地址, 端口
 * 使用DatagramSocket发送DatagramPacket
 * 关闭DatagramSocket
 */
public class Demo_SendClient {
    public static void main(String[] args) throws Exception {
    	Scanner scanner = new Scanner(System.in);       //创建键盘录入对象
		DatagramSocket socket= new DatagramSocket();    //创建Socket相当于创建码头
		while(true) {
			String line = scanner.nextLine();           //获取键盘录入的字符串
			byte[] b=line.getBytes("gbk");              //转换成gbk发送出去
			
			if("quit".equals(line)) {         
				break;
			}
			//注意参数的长度要的是字节的长度，不要把字符串的长度给传进去                 
			DatagramPacket packet=                      //创建Packet相当于集装箱
					new DatagramPacket(b,b.length, InetAddress.getByName("127.0.0.1"),6666);
			socket.send(packet);
		}
		socket.close();                                 //关闭码头
		scanner.close();                                //关闭输入流   
	}
}
