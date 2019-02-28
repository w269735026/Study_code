package com.wwl.socket.tcp;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
/*
1. 编写一个服务端可以给多个客户端发送图片。 （多线程）
 服务端
*/
public class SocketServer extends Thread{
	private Socket socket;
	static Set<String> set = new HashSet<String>();
	public SocketServer(Socket socket) {
		this.socket=socket;
	}
	public void run() {
		try {
			//获取socket的输出流对象
			OutputStream outputStream = socket.getOutputStream();
			//把数据写到浏览器上
			outputStream.write("<html><head><title>aaa</title></head><body>你好啊浏览器</body></html>".getBytes());
			String address = socket.getInetAddress().getHostAddress(); //获取ip
	        if(set.add(address)) {  //把访问者的ip存到set中
	             System.out.println("IP:"+address+"当前访问的次数是："+set.size());
	        }
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}
	public static void main(String[] args) throws IOException {
		ServerSocket server= new ServerSocket(9090);
		while(true) {
			 Socket socket = server.accept();
			 new SocketServer(socket).start();
		}
	}
	
}
