package com.om.chatapp.network;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JTextArea;

// Client Data read
public class ClientWorker extends Thread{
	private InputStream in;
	private JTextArea textArea;
	public ClientWorker(InputStream in, JTextArea textArea) {
		this.in=in;
		this.textArea=textArea;
	}
	
	public void run() {
		BufferedReader br=new BufferedReader(new InputStreamReader(in));
		String line;
		try {
			while(true) {
				line=br.readLine();// \n
				System.out.println("Line read..."+line);
				textArea.setText(textArea.getText()+line+"\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
			if(br!=null) {
				br.close();
			}}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
	}}
}