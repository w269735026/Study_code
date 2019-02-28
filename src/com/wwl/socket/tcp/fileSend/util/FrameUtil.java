package com.wwl.socket.tcp.fileSend.util;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

//初始化窗体的工具类
public class FrameUtil {

	
	public static void initJFrame(JFrame frame,int width , int height){
		Toolkit toolkit = Toolkit.getDefaultToolkit(); //获取一个与系统相关工具类对象
		//获取屏幕的分辨率
		Dimension d = toolkit.getScreenSize();
		
		int x = (int) d.getWidth();
		int y = (int) d.getHeight();
		
		frame.setBounds((x-width)/2, (y-height)/2, width, height);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void initFrame(Frame frame,int width , int height){
		Toolkit toolkit = Toolkit.getDefaultToolkit(); //获取一个与系统相关工具类对象
		//获取屏幕的分辨率
		Dimension d = toolkit.getScreenSize();
		
		int x = (int) d.getWidth();
		int y = (int) d.getHeight();
		
		frame.setBounds((x-width)/2, (y-height)/2, width, height);
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
	}
	
}
