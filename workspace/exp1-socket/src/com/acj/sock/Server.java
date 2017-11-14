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
			print("�����󣡡�[error]:�˿��ѱ�ռ��,��ʧ��!");
			return;
		} 	
		while(true){
			print("�ȴ��û�����...");
	    	clientSock = server.accept();
	    	print("�ѽ����û�����");
	        
	        /** ��ȡ�ͻ��˴�������Ϣ */
	        // ��Socket����õ�����������������Ӧ��BufferedReader����
	        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSock.getInputStream()));
	        // ��ȡ�ӿͻ��˶�����ַ���
	        String result = bufferedReader.readLine();
	        print("�û������ļ� : " + result);
	        try{
				File file = new File(pwd+result);
				FileInputStream fis = new FileInputStream(file);
				DataOutputStream dos = new DataOutputStream(clientSock.getOutputStream());
		      
				PrintWriter printWriter = new PrintWriter(clientSock.getOutputStream());
                printWriter.println("True");
                printWriter.flush();
				
				//�ļ����ͳ���
				dos.writeUTF(file.getName());
				dos.flush();
				dos.writeLong(file.length());
				dos.flush();
				//�����ļ�
				byte[] sendBytes = new byte[1024];
				int length = 0;
				while((length = fis.read(sendBytes, 0, sendBytes.length)) > 0){
					dos.write(sendBytes, 0, length);
				    dos.flush();   
				}
				fis.close();
				dos.close();
	        } catch (FileNotFoundException e) {
			    print("�Ҳ������ļ����ļ�����Ч��");
				PrintWriter printWriter = new PrintWriter(clientSock.getOutputStream());
                printWriter.println("False");
                printWriter.flush();
			}
	        finally{
		        clientSock.close();	
		        print("�ѹر��û�����");
	        }
	      
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