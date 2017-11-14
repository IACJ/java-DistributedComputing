package com.acj.sock;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    
    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 2017;
    
    private Socket serverSock;
    
    public Client(){
    	try {
    		try {	
				serverSock = new Socket(SERVER_IP, SERVER_PORT);
				
				// 向服务器请求文件
				/** 发送客户端准备传输的信息 */
				// 由Socket对象得到输出流，并构造PrintWriter对象
				PrintWriter printWriter = new PrintWriter(serverSock.getOutputStream(), true);
				// 将输入读入的字符串输出到Server
				print("请输入你想请求的文件");
				BufferedReader sysBuff = new BufferedReader(new InputStreamReader(System.in));
				printWriter.println(sysBuff.readLine());
				// 刷新输出流，使Server马上收到该字符串
				printWriter.flush();
				
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(serverSock.getInputStream()));
		        // 获取从客户端读入的字符串
		        String result = bufferedReader.readLine();
		        print(result);
		        if (result == "False"){
		        	print("找不到该文件，文件名无效");
		        	return;
		        }
				
				/**开始接收文件*/
				DataInputStream dis = new DataInputStream(serverSock.getInputStream());
				//文件名和长度
				String fileName = dis.readUTF();
				long fileLength = dis.readLong();
				FileOutputStream fos = new FileOutputStream(new File("c:/test/oo" + fileName));
				  
				byte[] sendBytes = new byte[1024];
				int transLen = 0;
				print("----开始接收文件<" + fileName + ">,文件大小为<" + fileLength + ">----");
				
				while(true){
					int read = 0;
					read = dis.read(sendBytes);
					if(read == -1){
						break;
					}
						
					transLen += read;
					System.out.println("接收文件进度" + 100 * transLen/fileLength + "%...");
					fos.write(sendBytes, 0, read);
					fos.flush();
				}
				print("----接收文件<" + fileName + ">成功-------");
				dis.close();
				fos.close();
            	} catch (Exception e) {
            		e.printStackTrace();
            	}finally{
                serverSock.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws Exception {
    	print("程序开始");
        new Client();
        print("程序结束");
    }
    public static void print(String s){
    	System.out.println("[Client]: "+s);
    }
}