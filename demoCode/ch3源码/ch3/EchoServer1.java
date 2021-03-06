import java.io.*;

public class EchoServer1 {
   public static void main(String[] args) {
      int serverPort = 1117;    // default port
      if (args.length == 1 )
         serverPort = Integer.parseInt(args[0]);       
      try {
         // instantiates a datagram socket for both sending and receiving data
   	     MyServerDatagramSocket mySocket = new MyServerDatagramSocket(serverPort); 
         System.out.println("Echo server ready.");  
         while (true) {  // forever loop
            DatagramMessage request = mySocket.receiveMessageAndSender();
            System.out.println("Request received");
            String message = request.getMessage( );
            System.out.println("message received: "+ message);
            // Now send the echo to the requestor
            mySocket.sendMessage(request.getAddress( ),
               request.getPort( ), message);
		   } //end while
       } // end try
	   catch (Exception ex) {
          ex.printStackTrace( );
	   } // end catch
   } //end main
} // end class  
