package client;

import java.awt.List;
import java.awt.TextArea;
import java.awt.TextField;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import common.Node;

public class ClientReceiveThread extends Thread {
	Socket socket;
	List list;
	TextArea taRecord;
	TextField tfCount;
	ObjectInputStream in;
	ObjectOutputStream out;
	Node node;
	InetAddress ip;
	int port;
	SocketAddress ip1;
	ServerSocket serverSocket;
	int selectedPort;
	TextArea taInput;
	
	public ClientReceiveThread(Node node, Socket socket, ObjectInputStream in,
			ObjectOutputStream out, List list, TextArea taRecord,
			TextArea taInput, TextField tfCount, InetAddress ip, int port,
			int selectedPort) {
		this.node = node;
		this.socket = socket;
		this.in = in;
		this.out = out;
		this.list = list;
		this.taRecord = taRecord;
		this.taInput = taInput;
		this.tfCount = tfCount;
		this.ip = ip;
		this.port = port;
		this.selectedPort = selectedPort;
	}
	
	public void run() {
		try {
			serverSocket = new ServerSocket(this.port);
			
			while(true) {
				Socket clientSocket = serverSocket.accept();
				ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
				String type = (String)in.readObject();
				
				if(type.equalsIgnoreCase("聊天信息")) {
					String name = (String)in.readObject();
					String mess = (String)in.readObject();
					taRecord.append(name + "说：" + "\n");
					taRecord.append(" " + mess + "\n");
				}
				
				///////////
				out.writeObject("A请求关闭连接！");
				out.flush();
				String shut2;
				while(true) {
					shut2 = (String)in.readObject();
					if(shut2.equalsIgnoreCase("B收到A请求关闭连接！")) {
						out.writeObject("A收到B的关闭确认！");
						out.flush();
						break;
					}
				}
				///////////
				
				out.close();
				in.close();
				clientSocket.close();
			}
		}catch(Exception e) {
			taRecord.append("error47" + e.toString());
		}
	}
}
