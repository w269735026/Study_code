package com.wwl.socket.tcp.fileSend.even;

import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import com.wwl.socket.tcp.fileSend.frame.MainFrame;

public class OpenButton implements ActionListener{
	private Frame frame;
	private String fileName;
	private JLabel fileLabelName;
	private String path;


	public OpenButton(Frame frame, String fileName, JLabel fileLabelName) {
		this.frame = frame;
		this.fileName = fileName;
		this.fileLabelName = fileLabelName;
	}
	
	public void actionPerformed(ActionEvent e) {
		//打开文件对话框
		FileDialog fileDialog = new FileDialog(frame,"请选择文件",FileDialog.LOAD);
		fileDialog.setVisible(true);
		String directoryName = fileDialog.getDirectory();//目录
		fileName = fileDialog.getFile();
		//把文件存储到MainFrame类中
		MainFrame.getMainFrame().setFileName(fileName);
		path = directoryName+fileName;
		MainFrame.getMainFrame().setPath(path);
		if(directoryName!=null) {
			fileLabelName.setText("");
			fileLabelName.setText(fileName);
			
		}
	}



	
}
