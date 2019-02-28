package com.wwl.socket.tcp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
/*
1. 编写一个服务端可以给多个客户端发送图片。 （多线程）
 客户端
*/

public class SocketClient {
	public static void main(String[] args) throws IOException, IOException {
		Socket socket = new Socket(InetAddress.getLocalHost(),9090);
		//获取输入流
		InputStream inputStream = socket.getInputStream();
		OutputStream output= new FileOutputStream("d:/cc.jpg");
		byte[] b= new byte[1024];
		int length = 0;
		while((length=inputStream.read(b)) != -1) {
			output.write(b, 0, length);
		}
		output.close();
		socket.close();
	}
}
