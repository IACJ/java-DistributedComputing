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
			print("【错误！】[error]:端口已被占用,绑定失败!");
			System.out.println("提示：请不要同时打开多个server程序");
			return;
		} 	
		while(true){
			print("等待用户连接...");
			System.out.println();
			MyStreamSocket sSock = new MyStreamSocket(server.accept());
			Thread thread = new Thread(new ServerThread(sSock));
			thread.start();
	    	print("已建立一个用户连接.");
		}
	}
    
	public static void main(String[] args) throws Exception {
		print("程序开始");
        new Server();
        print("程序结束");
    }
    public static void print(String s){
    	System.out.println("[Server]: "+s);
    }
}