package com.acj.streamsocket;

import java.net.ServerSocket;

public class Server {
	
	public static void main(String[] args) {
		try{
			final int PORT = 1234;
			ServerSocket serverSocket = new ServerSocket(PORT);
			MyStreamSocket mySocket = new MyStreamSocket(serverSocket.accept());
			
			String message = mySocket.receiveMessage();
			System.out.println(message);
			
			mySocket.close();
			serverSocket.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
