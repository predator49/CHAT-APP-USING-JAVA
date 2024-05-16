package com.om.chatapp.network;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Iterator;
 
public class ServerWorker extends Thread {
	
	private Socket clientSocket;
	private InputStream in;
	private OutputStream out;
	private Server server;
	public ServerWorker(Socket clientSocket, Server server) throws IOException {
		this.server=server;
		this.clientSocket=clientSocket;
		in=clientSocket.getInputStream();
		out=clientSocket.getOutputStream();
		System.out.println("New Client Comes...");     
	}
	
	public void run() {
		// Read data from the client and broadcast the data to all
		BufferedReader br= new BufferedReader(new InputStreamReader(in));
		String line;
		try {
			while(true) {
				line = br.readLine();// \n
				System.out.println("Line read..."+line);
				if(line.equalsIgnoreCase("quit")) {
					break; //client chat ends
				}
//				out.write(line.getBytes());
				// broadcast to all clients
				for(ServerWorker serverWorker : server.workers) {
					line=line+"\n";
					serverWorker.out.write(line.getBytes());
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			try {
			if(br!=null) {
				br.close();
			}
			if(in!=null) {
				in.close();
			}
			if(out!=null) {
				out.close();
			}
			if(clientSocket!=null) {
				clientSocket.close();
			}
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
}