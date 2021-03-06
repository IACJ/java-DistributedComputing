package homework_3_2_4;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketTimeoutException;

public class ServerSock {

	final static int MAX_LEN = 100;
	final static String PORT = "8888";
	private static final int TIME_OUT = 5000;

	public static void main(String[] args) {

		System.out.println("ServerSock begin:");
		
		int port = Integer.parseInt(PORT);
		
		try {
			DatagramSocket mySocket = new DatagramSocket(port);
   	      	System.out.println("Waiting for receiving the data!");  
   	      	// instantiates a datagram socket for receiving the data
            byte[ ] buffer = new byte[MAX_LEN];                                     
            DatagramPacket datagram = new DatagramPacket(buffer, MAX_LEN);
            mySocket.setSoTimeout(TIME_OUT);  
            
           
            Thread.sleep(10000);
            System.out.println("Message receiving.");
            
            
            mySocket.receive(datagram);
            String message = new String(buffer);
            System.out.println(message);
            mySocket.close( );
        }catch (SocketTimeoutException ex) {
        	System.out.println("���ճ�ʱ��");
		}catch (Exception ex) {
        	ex.printStackTrace( );
		}
		System.out.println("ServerSock end.");
	}
}
