package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ClientSendThread extends Thread {
	private InetAddress remoteAddress = null;
	private int remotePort = 0;
	private String message = null;
	private String name=null;
	
	public ClientSendThread(InetAddress address, int port, String message,String name){
		this.remoteAddress = address;
		this.remotePort = port;
		this.message = message;
		this.name=name;
	}
	
	public void run(){
		try {
			Socket socket = new Socket(remoteAddress, remotePort);
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream in  = new ObjectInputStream(socket.getInputStream());
			out.writeObject("������Ϣ");
			out.writeObject(name);
			out.writeObject(this.message);
			out.flush();
			
			String shut1;
			while(true) {
				shut1 = (String)in.readObject();
				if(shut1.equalsIgnoreCase("A����ر����ӣ�")) {
					out.writeObject("B�յ�A����ر����ӣ�");
					out.flush();
					break;
				}
			}
			
			String shut3;
			while(true) {
				shut3 = (String)in.readObject();
				if(shut3.equalsIgnoreCase("A�յ�B�Ĺر�ȷ�ϣ�"))
					break;
			}
			
			out.close();
			socket.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
