import java.net.*;
import java.io.*;	

public class Example1Receiver {
   public static void main(String[] args) {
      if (args.length < 1)
         System.out.println
            ("This program requires a command line argument.");
      else {
			int port = Integer.parseInt(args[0]);
         	final int MAX_LEN = 10;
		// This is the assumed maximum byte length of the datagram to be received.
			try {
   	      	DatagramSocket	mySocket = new DatagramSocket(port);
   	      	System.out.println("Waiting for receiving the data!");  
		// instantiates a datagram socket for receiving the data
            byte[ ] buffer = new byte[MAX_LEN];                                     
            DatagramPacket datagram = 
               new DatagramPacket(buffer, MAX_LEN);
            //mySocket.setSoTimeout(6000);  
            for(int i=0;i<10;i++)
            {
            	//mySocket.setSoTimeout(5000);
            	mySocket.receive(datagram);
            	String message = new String(buffer);
            	System.out.println(message);
            }
            mySocket.close( );
         	} // end try
	 	catch (Exception ex) {
        	ex.printStackTrace( );
		}
      } // end else
   } // end main
} // end class
