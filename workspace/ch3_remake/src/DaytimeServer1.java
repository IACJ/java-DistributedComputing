import java.io.*;
import java.util.Date;   // for obtaining a timestamp

public class DaytimeServer1 {
   public static void main(String[] args) {
      int serverPort = 13;    // default port
      if (args.length == 1 )
         serverPort = Integer.parseInt(args[0]);       
      try {
         // instantiates a datagram socket for both sending and receiving data
   	   	 MyServerDatagramSocket mySocket = new MyServerDatagramSocket(serverPort); 
         System.out.println("Daytime server ready.");  
         while (true) {  // forever loop
            DatagramMessage request = mySocket.receiveMessageAndSender();
            System.out.println("Request received");
            Date timestamp = new Date ();
            System.out.println("timestamp sent: "+ timestamp.toString());
            // Now send the reply to the requestor
            mySocket.sendMessage(request.getAddress( ),
                       request.getPort( ), timestamp.toString( ));
		   } //end while
       } // end try
	    catch (Exception ex) {
        	System.out.println("There is a problem: " + ex);
	    } // end catch
   } //end main
} // end class  
