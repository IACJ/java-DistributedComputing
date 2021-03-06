package com.acj.sock;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.ConnectException;

public class Client {
    
    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 2017;
    
    private MyStreamSocket cSock;
    
    public Client(){
    	try {
    		try {	
				cSock = new MyStreamSocket(SERVER_IP, SERVER_PORT);
	
				print("请输入你想请求的文件");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				cSock.sendMessage(br.readLine());
				
		        String msg = cSock.receiveMessage();
		        if ( "False".equals(msg)){
		        	print("找不到该文件，文件名无效");
		        	return;
		        }else{
		        	print("请等待...");
		        }
		        String fileName = cSock.receiveMessage();
		        long fileLength = Long.parseLong(cSock.receiveMessage());
		        
		        int breakpoint = 0;			
				File file = new File("c:/test/out_"+fileName+".mytemp");
				if(file.exists()){  
					breakpoint = (int) file.length();  
		        }  
		        cSock.sendMessage(breakpoint+"");
		        
		        RandomAccessFile access = new RandomAccessFile(file,"rw");
		        /**开始接收文件*/
		        access.skipBytes(breakpoint);
		        
				byte[] sendBytes = new byte[1024];
				long transLen = breakpoint;
				while(true){
					int read = 0;
					read = cSock.receiveData(sendBytes);
					if(read == -1){
						break;
					}
					transLen += read;
					System.out.println("接收文件进度" + 100 * transLen/fileLength + "%...");
					access.write(sendBytes, 0, read);
				}
				access.close();
				if (fileLength == file.length()){
					
					File newFile = new File("c:/test/out_"+fileName);
					int test = 0;
					while(newFile.exists()){ 
						test += 1;
						newFile  =  new File("c:/test/out_("+test+")_"+fileName);
				
			        }  
					file.renameTo(newFile); 
					print("----接收文件<" + newFile + ">成功-------");
				}else{
					throw(new Exception("传输异常"));
				}
				
            	} catch (ConnectException e) {
            		e.printStackTrace();
            		print("你大概没开服务器");
            	}finally{
            		cSock.close();
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