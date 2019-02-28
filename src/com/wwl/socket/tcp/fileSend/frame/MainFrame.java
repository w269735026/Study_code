package com.wwl.socket.tcp.fileSend.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.TextField;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.wwl.socket.tcp.fileSend.even.OpenButton;
import com.wwl.socket.tcp.fileSend.even.SaveButton;
import com.wwl.socket.tcp.fileSend.even.SendButton;
import com.wwl.socket.tcp.fileSend.server.ServerFile;
import com.wwl.socket.tcp.fileSend.util.FrameUtil;

public class MainFrame {
	private  String path;
	private TextField post;    //发送端端口号
	private TextField serverPost;//服务端端口号
	private TextField ip;
	private String fileName;   //文件名
	private static final MainFrame mf = new MainFrame();
	private JLabel dirName;  //保存目录
	private MainFrame() {
	}
	public static MainFrame getMainFrame() {
			return mf;
	}
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public static void main(String[] args) {
		MainFrame.getMainFrame().mainJFrame();
	}
	/**
	 * 整体布局
	 */
	public void mainJFrame() {
		final JFrame frame= new JFrame("传送文件");
		//创建面板北方和南方
		JPanel panelSOUTH= new JPanel();
		JPanel panelNORTH= new JPanel();
		panelSOUTH.setBackground(Color.WHITE);
		panelNORTH.setBackground(Color.WHITE);
		frame.add(panelSOUTH, BorderLayout.SOUTH);
		frame.add(panelNORTH, BorderLayout.NORTH);
		
		//北方布局
		northLayout(frame, panelNORTH);
		//南方布局
		southLayout(frame,panelSOUTH);
		panelSOUTH.setVisible(true);
		panelNORTH.setVisible(true);
		FrameUtil.initJFrame(frame, 500, 500);
	}
	
	/**
	 * 北边布局方法
	 * @param frame
	 * @param panelNORTH
	 */
	private void northLayout(final JFrame frame, JPanel panelNORTH) {
		
		//界面ip的设置
		JLabel labelIp=new JLabel("IP:");
		ip = new TextField();
		ip.setColumns(10);
		ip.setText("127.0.0.1");
		panelNORTH.add(labelIp);
		panelNORTH.add(ip);
		
		//界面host的设置
		JLabel postLabel=new JLabel("端口:");
		post = new TextField();
		post.setColumns(1);
		post.setText("6666");
		panelNORTH.add(postLabel);
		panelNORTH.add(post);
		
		//获取文件路径
		JButton fileButton = new JButton("选择文件");  //按钮
		JLabel dirName = new JLabel("未选择文件");   
		panelNORTH.add(fileButton);    //添加到面板中
		panelNORTH.add(dirName);
		OpenButton open = new OpenButton(frame, fileName, dirName);//事件
		fileButton.addActionListener(open);
		
		//发送文件
		JButton sendButton = new JButton("发送");
		panelNORTH.add(sendButton);
		SendButton sb= new SendButton(ip, post,frame);//事件
		sendButton.addActionListener(sb);
	}
	/**
	 * 南边布局方法
	 * @param frame
	 * @param panelsouth
	 */
	private void southLayout(final JFrame frame, JPanel panelsouth) {	
		//界面host的设置
		JLabel postLabel=new JLabel("端口:");
		serverPost = new TextField();
		serverPost.setColumns(1);
		serverPost.setText("6666");
		panelsouth.add(postLabel);
		panelsouth.add(serverPost);
		
		//保存文件路径的设置
		JButton saveButton = new JButton("保存目录");
		dirName = new JLabel("未选择文件");
		panelsouth.add(saveButton);
		panelsouth.add(dirName);
		//事件
		saveButton.addActionListener(new SaveButton(dirName));
		JButton serverButton = new JButton("开启服务");
		panelsouth.add(serverButton);
		//开启服务事件
		ServerFile serverFile = new ServerFile(serverButton,dirName,frame,serverPost);
		serverButton.addActionListener(serverFile);
	}
}
