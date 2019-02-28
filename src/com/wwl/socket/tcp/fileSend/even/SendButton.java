package com.wwl.socket.tcp.fileSend.even;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.wwl.socket.tcp.fileSend.frame.MainFrame;
/**
 * 发送端：开启线程：使用线程池
 * @author Administrator
 *
 */
public class SendButton implements ActionListener {
	private TextField ip;
	private TextField post;
	private JFrame frame;
	//使用线程池
	ExecutorService  es=Executors.newFixedThreadPool(2);
	public SendButton(TextField ip, TextField post,JFrame frame) {
		this.ip = ip;
		this.post = post;
		this.frame=frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
			this.run();
	}
	
	public void run() {
		//线程池的方法使用，永远不关闭
		es.submit(new Runnable() {
			
			@Override
			public void run() {
				String fileName = MainFrame.getMainFrame().getFileName();
				//System.out.println("发送端获取的文件名："+fileName);
				//ip的获取
				String textIP = ip.getText();
				//端口的获取
				String textPost = post.getText();
				String path = MainFrame.getMainFrame().getPath();
				try {
					if("".equals(textIP) || "".equals(textPost) || path ==null) {
						return;
					}else {
						Socket socket= new Socket(textIP,Integer.parseInt(textPost));
						OutputStream outputStream = socket.getOutputStream();
						//先发文件名称
						byte[] bytes = fileName.getBytes(); //1、获取文件名的长度
						byte[] bName=new byte[50];          //2、定义byte数组，和服务端最好一致，
						System.arraycopy(bytes, 0, bName, 0, bytes.length); //3、使用方法把数组拷贝到指定长度的数组中
						outputStream.write(bName);          //4、发送长度为50的数组过去,服务端就接收这50个字节
						FileInputStream input= new FileInputStream(path);
						byte[] b= new byte[1024*1024*5];
						int len;
						while((len=input.read(b)) !=-1) {
							System.out.println(len+"  :");
							outputStream.write(b, 0, len);
						}
						input.close();
						socket.close();
					}
				} catch (ConnectException e1) {
					JOptionPane.showMessageDialog(frame,"服务端未开启","警告",JOptionPane.WARNING_MESSAGE);
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
	}
}
