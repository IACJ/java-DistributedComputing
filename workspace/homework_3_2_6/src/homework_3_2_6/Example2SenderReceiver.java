package homework_3_2_6;
import java.net.InetAddress;

public class Example2SenderReceiver {

// An application which sends then receives a message using connectionless datagram socket.
   public static void main(String[] args) {
      if (args.length != 4)
         System.out.println("This program requires four command line arguments");
      else {
         try {      
  		      InetAddress receiverHost = InetAddress.getByName(args[0]);
              int receiverPort = Integer.parseInt(args[1]);
  		      int myPort = Integer.parseInt(args[2]);
              String message = args[3];
   	          MyDatagramSocket mySocket = new MyDatagramSocket(myPort);  
              // instantiates a datagram socket for both sending and receiving data
   	          
   	          for (;;){
   	              mySocket.sendMessage( receiverHost, receiverPort, message);
   	              System.out.println("Message Send.");
   	              System.out.println("Message receiving.");
   	              System.out.println(mySocket.receiveMessage());
	              System.out.println("Sleeping.");
   	              Thread.sleep(100);
   	          }     
			  //mySocket.close( );
         } // end try
	      catch (Exception ex) {
            ex.printStackTrace( );
	      } //end catch
      } //end else
   } //end main 

} //end class

