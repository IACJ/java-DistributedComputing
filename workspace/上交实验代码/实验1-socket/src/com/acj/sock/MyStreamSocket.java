package com.acj.sock;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

public class MyStreamSocket {
	
	private Socket  socket;
	private BufferedReader input;
	private PrintWriter output;
	private DataOutputStream dos;
	private DataInputStream dis;

	   
	MyStreamSocket(String serverIp,
			int acceptorPort ) throws SocketException, IOException{

		socket = new Socket(serverIp, acceptorPort );
		setStreams( );
	}
	MyStreamSocket(Socket socket)  throws IOException {
		this.socket = socket;
		setStreams( );
	}
	private void setStreams( ) throws IOException{
		InputStream inStream = socket.getInputStream();
		input = new BufferedReader(new InputStreamReader(inStream));
		OutputStream outStream = socket.getOutputStream();
		output = new PrintWriter(new OutputStreamWriter(outStream));
		dos = new DataOutputStream(socket.getOutputStream());
		dis = new DataInputStream(socket.getInputStream());
	}
	public void sendMessage(String message)  {	
		output.println(message); 
		output.flush(); 
	} 
	public String receiveMessage( )  {
		String message = null;
		try {
			message = input.readLine( );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return message;
	}
	public void close(){
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void sendData(byte[] sendBytes, int i, int length) throws IOException {
			dos.write(sendBytes, i, length);
	}
	public int receiveData(byte[] sendBytes) {
		int read = -1;
		try {
			read = dis.read(sendBytes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return read;
	}

}
