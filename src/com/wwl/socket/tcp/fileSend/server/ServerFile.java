package com.wwl.socket.tcp.fileSend.server;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * 服务端内部类：开启另一条线程：
 * 注意：我要重复使用：需使用到线程池：不是线程池，只执行一次线程就死亡了，不能执行第二次
 * @author Administrator
 *
 */
public class ServerFile implements ActionListener{
	private JButton serverButton; //换成：'关闭服务'
	private ServerSocket server;
	private JLabel dirName; 
	private JFrame frame;
	private TextField serverPost;//服务端端口号
	//使用线程池
	ExecutorService  es=Executors.newFixedThreadPool(2);
	
	public ServerFile(JButton serverButton,JLabel dirName,JFrame frame,TextField serverPost) {
		this.serverButton = serverButton;
		this.dirName=dirName;
		this.frame=frame;
		this.serverPost=serverPost;
	}
	public void actionPerformed(ActionEvent e) {
		String text = serverButton.getText();
		if("开启服务".equals(text)) {
			if(dirName ==null || "未选择文件".equals(dirName.getText())) {
				JOptionPane.showMessageDialog(frame,"请先选择保存目录！","警告",JOptionPane.WARNING_MESSAGE);
				return;
			}
			serverButton.setText("关闭服务");
			JOptionPane.showMessageDialog(frame, "开启传输服务", "通知",JOptionPane.INFORMATION_MESSAGE);
				this.run();
		}else {
			serverButton.setText("开启服务");
			try {
				if(server !=null) {
					server.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void run() {
		es.submit(new Runnable() {
		@Override
		public void run() {
			try {
				
				server = new ServerSocket(Integer.parseInt(serverPost.getText()));
				while(true) {
					Socket socket = server.accept();
					//获取目录路径：dirName.getText() 配合系统的不同 分隔符："/"
					String fengefu=dirName.getText().replace("\\", File.separator);
					InputStream inputStream = socket.getInputStream();
					//接收文件名字
					byte[] fileByte=new byte[50];  //1、定义长度50的数组
					inputStream.read(fileByte,0,50);//2、只获取流长度为50的字节(虽然客户端是分两次发送的，但是测试服务端都是一次性接收的)
					String name=new String(fileByte,0,50).trim();//3、获取名字，因为还有没有填满的都变成空字符串，去除空字符串
					//以下流程是文件的接收：代码不变
					byte[] b= new byte[1024*1024];
					int len = 0;
					FileOutputStream output = new FileOutputStream(fengefu+File.separator+name);
					while((len=inputStream.read(b)) !=-1) {
						output.write(b, 0, len);
						output.flush();
					}
					output.close();
				}
			} catch (SocketException e) {
				JOptionPane.showMessageDialog(frame, "已关闭服务", "通知",JOptionPane.INFORMATION_MESSAGE);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		});
	}	
}
