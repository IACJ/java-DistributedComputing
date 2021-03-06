package homework_3_2_4;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientSock {

		
	final static int MAX_LEN = 100;
	final static String PORT = "8888";
	private static final int TIME_OUT = 5000;

	public static void main(String[] args) {
		System.out.println("ClientSock begin:");
		try {
			InetAddress receiverHost = InetAddress.getByName("localhost");
			int receiverPort = Integer.parseInt(PORT);
			String message = "hello your mom";
			// instantiates a datagram socket for sending the data
			DatagramSocket	mySocket = new DatagramSocket();           
			byte[ ] buffer = message.getBytes( );                                     
			DatagramPacket datagram = new DatagramPacket(buffer, buffer.length, receiverHost, receiverPort);                           
			mySocket.setSoTimeout(TIME_OUT);
		    mySocket.send(datagram);
		    mySocket.close( );
		}catch (Exception ex) {
			ex.printStackTrace( );
		}
		System.out.println("ClientSock end.");
	}
		
}
