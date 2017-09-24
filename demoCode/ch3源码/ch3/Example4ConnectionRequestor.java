import java.net.*;
import java.io.*;

public class Example4ConnectionRequestor {
   public static void main(String[] args) {
      if (args.length != 2)
         System.out.println
            ("This program requires two command line arguments");
      else {
         try {
  		    InetAddress acceptorHost = InetAddress.getByName(args[0]);
  		    int acceptorPort = Integer.parseInt(args[1]);
            // instantiates a data socket and connect with a timeout
            SocketAddress sockAddr= new InetSocketAddress(acceptorHost, acceptorPort);
   	        Socket mySocket = new Socket(); 
            int  timeoutPeriod = 5000;    // 2 seconds
            mySocket.connect(sockAddr, timeoutPeriod); 
       		System.out.println("Connection request granted");	
        // get an input stream for reading from the data socket
            InputStream inStream = mySocket.getInputStream();
            // create a BufferedReader object for text line input
            BufferedReader socketInput = 
               new BufferedReader(new InputStreamReader(inStream));
       		 System.out.println("waiting to read");
        // read a line from the data stream
            String message = socketInput.readLine( );
        	System.out.println("Message received:");
            System.out.println("\t" + message);
            mySocket.close( );
     System.out.println("data socket closed");
         } // end try
	 	catch (Exception ex) {
        	ex.printStackTrace( );
	  	}
      } // end else
   } // end main
} // end class
