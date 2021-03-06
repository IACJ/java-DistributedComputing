package com.acj.sock;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.net.SocketException;

public class ServerThread implements Runnable{
    private static String pwd = "c:/test/";
	MyStreamSocket sock;
	String msg;
	
	public ServerThread(MyStreamSocket myStreamSocket) {
		this.sock = myStreamSocket;
	}
	public void run (){
		msg = sock.receiveMessage();
		print("用户请求文件 : " + msg);
		try{
			File file = new File(pwd+msg);
			RandomAccessFile access = new RandomAccessFile(file,"r");
			sock.sendMessage("True");
			
			sock.sendMessage(file.getName());
			sock.sendMessage("" + file.length() );
			
			int startIndex = 0;
			startIndex = Integer.parseInt(sock.receiveMessage());
		
			/* 开始传输文件 */
			byte[] sendBytes = new byte[1024];
			int length = 0;
			access.skipBytes(startIndex);
			while((length = access.read(sendBytes, 0, sendBytes.length)) > 0){
				sock.sendData(sendBytes, 0, length);
			}			
			access.close();
			print("传输成功.");
        } catch (FileNotFoundException e) {
		    print("找不到该文件，文件名无效！");
		    sock.sendMessage("False");
		} catch(SocketException e){
			print("Socket异常: 客户端强制断开连接");
			
		}catch(Exception e){
			
			e.printStackTrace();
			print("异常！");
		}finally{
			sock.close();	
	        print("已关闭用户连接");
        }
	}
    public static void print(String s){
    	System.out.println("[Server-Thread]: "+s);
    }

}
