package com.acj.myServer;

import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;



public class MyServer {

	private static final int PORT = 8080;
	private ServerSocket server;
	public static final String WEB_ROOT = "c:/test/";

	public MyServer() {
		try {
			server = new ServerSocket(PORT);
			print("已监听端口:"+PORT);
	        System.out.println();
		}catch (BindException e){
			e.printStackTrace();
			print("【错误！】[error]:端口已被占用,绑定失败!");
			print("提示：请不要同时打开多个server程序");
			System.exit(1);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	public void run(){
		while (true) {
			Socket socket = null;
            try {
                socket = server.accept();      
    			Thread thread = new Thread(new ServerThread(socket));
    			thread.start();  
            }catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
	}
	
	public static void main(String[] args) {
		print("服务开始运行~");	
		MyServer myServer = new MyServer();
		myServer.run();
	}
    public static void print(String s){
    	System.out.println("[Server]: "+s);

    }
}
