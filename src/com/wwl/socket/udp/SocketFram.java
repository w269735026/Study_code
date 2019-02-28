package com.wwl.socket.udp;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.wwl.socket.udp.util.FrameUtil;


public class SocketFram {
	private  TextArea sendText;
	private  TextArea receiveText;
	private  TextField textField;
	private  DatagramSocket ds;     //建立码头
	
	public SocketFram() {
		//接收方法的线程开启
		new ReceiveClass().start();
		try {
			ds= new DatagramSocket();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		SocketFram sf = new SocketFram();
		sf.extracted(sf);
	}

	private void extracted(final SocketFram s) {
		Frame frame= new Frame("聊天窗口");
	    Panel panelSOUTH= new Panel();   //南方面板
	    Panel panelNORTH= new Panel();  //北方面板
	    
	    textField = new TextField();
	    textField.setText("192.168.0.102");
	    panelSOUTH.add(new Label("IP"));
	    textField.setColumns(10);;
	    panelSOUTH.add(textField);
	    Button sendButton= new Button("发送");
	    sendButton.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				try {
					s.send();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
	    	
		});
	    Button saveButton= new Button("聊天记录");
	    Button shakeButton= new Button("震动");
	    panelSOUTH.add(sendButton);
	    panelSOUTH.add(saveButton);
	    panelSOUTH.add(shakeButton);
	  /*-----------------南方面板设置end---------------------------------*/
	    
	    //
	    panelNORTH.setLayout(new BorderLayout());//panel
	    sendText = new TextArea(3,1);
	  //参数一 ：如宋体(老师说设置什么都没用)，参数二：PLAIN样式，参数三：字体大小
	    sendText.setFont(new Font("", Font.PLAIN, 15));  
	    receiveText = new TextArea(21,1);
	    receiveText.setEditable(false); // 不能编辑
	    receiveText.setBackground(Color.WHITE); //背景色为白色
	    receiveText.setFont(new Font("", Font.PLAIN, 15));
	    
	    panelNORTH.add(sendText,BorderLayout.SOUTH);
	    panelNORTH.add(receiveText,BorderLayout.NORTH);
	    /*-----------------end---------------------------------*/
	    
	    frame.add(panelNORTH, BorderLayout.NORTH);
	    frame.add(panelSOUTH, BorderLayout.SOUTH);
	    panelSOUTH.setVisible(true);
	    panelNORTH.setVisible(true);
		
		FrameUtil.initFrame(frame, 350, 513);
	}
	
	public void send() throws IOException{
		  //把建立码头抽取出来就不用发送一次又创建一次对象
		String ip=textField.getText();    //获取用户输入的ip
		String content = sendText.getText();
		byte[] b = content.getBytes();
		DatagramPacket dp= new DatagramPacket(b, b.length, InetAddress.getByName(ip), 6666);   //集装箱：装入的数据和大小：联系人，哪个码头
		ds.send(dp);                                  //发送集装箱
		//int port = dp.getPort();
		//String address = dp.getAddress().getHostAddress();
		receiveText.append(time()+" 【我】说：\r\n"+content+"\r\n\r\n");
		//ds.close();
	}
	//当前时间方法
	private String time() {
		Date date = new Date();
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy年MM年dd日 HH:mm:ss");
		return sdf.format(date);
	}
	//匿名内部类
	public class ReceiveClass extends Thread{
		public void run() {
			try {
				DatagramSocket ds= new DatagramSocket(6666); //准备码头
				while(true) {
					DatagramPacket dp= new DatagramPacket(new byte[4096],4096);   //码头中集装箱大小
					ds.receive(dp);                 //用集装箱接收货物
					byte[] b=dp.getData();          //获取货物
					int i=dp.getLength();           //获取货物的大小
					String address = dp.getAddress().getHostAddress();
					String content=new String(b,0,i);
					receiveText.append(time()+" 【"+address+"】说：\r\n"+content+"\r\n\r\n");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    
		}
	}
}
