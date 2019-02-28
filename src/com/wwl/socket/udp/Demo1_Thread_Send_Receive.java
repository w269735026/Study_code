package com.wwl.socket.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

/**
 *开启两个线程：让发送端和接收端都在控制台输出
 */
public class Demo1_Thread_Send_Receive {
	//main方法只用来开启线程
    public static void main(String[] args) throws Exception {
    	new Send().start();
    	new Receive().start();
    }
}
//发送端代码都放在run方法中
class Send extends Thread{
	public void run() {
		try {
			Scanner scanner = new Scanner(System.in);       //创建键盘录入对象
			DatagramSocket socket= new DatagramSocket();
			while(true) {
				String line = scanner.nextLine();           //获取键盘录入的字符串
				//byte[] b=line.getBytes("gbk");              //转换成gbk发送出去
				
				if("quit".equals(line)) {         
					break;
				}
				DatagramPacket packet=                      //创建Packet相当于集装箱
						new DatagramPacket(line.getBytes(),line.getBytes().length, InetAddress.getByName("127.0.0.1"),6666);
				socket.send(packet);
			}
			socket.close();                                 //关闭码头
			scanner.close();                                //关闭输入流   
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    //创建Socket相当于创建码头
		}
	}
//接收端代码都放在run方法中
class Receive extends Thread{
	public void run() {
		try {
			DatagramSocket socket = new DatagramSocket(6666);//创建Socket相当于创建码头
			DatagramPacket packet= new DatagramPacket(new byte[1024], 1024);//创建Packet相当于创建集装箱
			while(true) {
				socket.receive(packet);                         //接货,接收数据
				byte[] b = packet.getData();                    //获取数据
				int len =packet.getLength();                    //获取有效的字节个数
				String ip=packet.getAddress().getHostAddress(); //获取ip地址
				int port=packet.getPort();                      //获取端口号
				String str=new String(b,0,len);                 //转换成字符串
				System.out.println(ip+":"+":"+port+":"+str);    //打印
			} 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}      
		
	}
}