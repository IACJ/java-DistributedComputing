package com.acj.sock;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	private static final int PORT = 2017;
    private static String pwd = "c:/test/";
    private ServerSocket server;
    private Socket clientSock;
    
    public Server() throws Exception{
    	
		try {
			server = new ServerSocket(PORT);
		}catch (BindException e){
			e.printStackTrace();
			print("【错误！】[error]:端口已被占用,绑定失败!");
			return;
		} 	
		while(true){
			print("等待用户连接...");
	    	clientSock = server.accept();
	    	print("已建立用户连接");
	        
	        /** 获取客户端传来的信息 */
	        // 由Socket对象得到输入流，并构造相应的BufferedReader对象
	        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSock.getInputStream()));
	        // 获取从客户端读入的字符串
	        String result = bufferedReader.readLine();
	        print("用户请求文件 : " + result);
	        try{
				File file = new File(pwd+result);
				FileInputStream fis = new FileInputStream(file);
				DataOutputStream dos = new DataOutputStream(clientSock.getOutputStream());
		      
				PrintWriter printWriter = new PrintWriter(clientSock.getOutputStream());
                printWriter.println("True");
                printWriter.flush();
				
				//文件名和长度
				dos.writeUTF(file.getName());
				dos.flush();
				dos.writeLong(file.length());
				dos.flush();
				//传输文件
				byte[] sendBytes = new byte[1024];
				int length = 0;
				while((length = fis.read(sendBytes, 0, sendBytes.length)) > 0){
					dos.write(sendBytes, 0, length);
				    dos.flush();   
				}
				fis.close();
				dos.close();
	        } catch (FileNotFoundException e) {
			    print("找不到该文件，文件名无效！");
				PrintWriter printWriter = new PrintWriter(clientSock.getOutputStream());
                printWriter.println("False");
                printWriter.flush();
			}
	        finally{
		        clientSock.close();	
		        print("已关闭用户连接");
	        }
	      
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