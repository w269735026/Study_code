package com.wwl.socket.tcp.fileSend.even;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JLabel;

public class SaveButton implements ActionListener{
	private JLabel fileLabelName; 
	
	public SaveButton(JLabel fileLabelName) {
		this.fileLabelName = fileLabelName;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();
		// DIRECTORIES_ONLY就是只选目录
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int r = chooser.showOpenDialog(null); //0是选择确定，1是取消
		if(r==0) {
			File file = chooser.getSelectedFile();
			fileLabelName.setText(file.getPath());
		}
	}

}
