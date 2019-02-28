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
	private  DatagramSocket ds;     //������ͷ
	
	public SocketFram() {
		//���շ������߳̿���
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
		Frame frame= new Frame("���촰��");
	    Panel panelSOUTH= new Panel();   //�Ϸ����
	    Panel panelNORTH= new Panel();  //�������
	    
	    textField = new TextField();
	    textField.setText("192.168.0.102");
	    panelSOUTH.add(new Label("IP"));
	    textField.setColumns(10);;
	    panelSOUTH.add(textField);
	    Button sendButton= new Button("����");
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
	    Button saveButton= new Button("�����¼");
	    Button shakeButton= new Button("��");
	    panelSOUTH.add(sendButton);
	    panelSOUTH.add(saveButton);
	    panelSOUTH.add(shakeButton);
	  /*-----------------�Ϸ��������end---------------------------------*/
	    
	    //
	    panelNORTH.setLayout(new BorderLayout());//panel
	    sendText = new TextArea(3,1);
	  //����һ ��������(��ʦ˵����ʲô��û��)����������PLAIN��ʽ���������������С
	    sendText.setFont(new Font("", Font.PLAIN, 15));  
	    receiveText = new TextArea(21,1);
	    receiveText.setEditable(false); // ���ܱ༭
	    receiveText.setBackground(Color.WHITE); //����ɫΪ��ɫ
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
		  //�ѽ�����ͷ��ȡ�����Ͳ��÷���һ���ִ���һ�ζ���
		String ip=textField.getText();    //��ȡ�û������ip
		String content = sendText.getText();
		byte[] b = content.getBytes();
		DatagramPacket dp= new DatagramPacket(b, b.length, InetAddress.getByName(ip), 6666);   //��װ�䣺װ������ݺʹ�С����ϵ�ˣ��ĸ���ͷ
		ds.send(dp);                                  //���ͼ�װ��
		//int port = dp.getPort();
		//String address = dp.getAddress().getHostAddress();
		receiveText.append(time()+" ���ҡ�˵��\r\n"+content+"\r\n\r\n");
		//ds.close();
	}
	//��ǰʱ�䷽��
	private String time() {
		Date date = new Date();
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy��MM��dd�� HH:mm:ss");
		return sdf.format(date);
	}
	//�����ڲ���
	public class ReceiveClass extends Thread{
		public void run() {
			try {
				DatagramSocket ds= new DatagramSocket(6666); //׼����ͷ
				while(true) {
					DatagramPacket dp= new DatagramPacket(new byte[4096],4096);   //��ͷ�м�װ���С
					ds.receive(dp);                 //�ü�װ����ջ���
					byte[] b=dp.getData();          //��ȡ����
					int i=dp.getLength();           //��ȡ����Ĵ�С
					String address = dp.getAddress().getHostAddress();
					String content=new String(b,0,i);
					receiveText.append(time()+" ��"+address+"��˵��\r\n"+content+"\r\n\r\n");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    
		}
	}
}
