package com.acj.sock;

import java.net.BindException;
import java.net.ServerSocket;

public class Server {
	
	private static final int PORT = 2017;

    private ServerSocket server;
    
    public Server() throws Exception{
		try {
			server = new ServerSocket(PORT);
		}catch (BindException e){
			e.printStackTrace();
			print("�����󣡡�[error]:�˿��ѱ�ռ��,��ʧ��!");
			System.out.println("��ʾ���벻Ҫͬʱ�򿪶��server����");
			return;
		} 	
		while(true){
			print("�ȴ��û�����...");
			System.out.println();
			MyStreamSocket sSock = new MyStreamSocket(server.accept());
			Thread thread = new Thread(new ServerThread(sSock));
			thread.start();
	    	print("�ѽ���һ���û�����.");
		}
	}
    
	public static void main(String[] args) throws Exception {
		print("����ʼ");
        new Server();
        print("�������");
    }
    public static void print(String s){
    	System.out.println("[Server]: "+s);
    }
}