import java.net.*;

public class DaytimeClientHelper1 {
	public static String getTimestamp(String hostName, String portNum){  
      String timestamp = ""; 
      try {      
  		    InetAddress serverHost = InetAddress.getByName(hostName);
  		    int serverPort = Integer.parseInt(portNum);
        	// instantiates a datagram socket for both sending nd receiving data
			MyDatagramSocket mySocket = new MyDatagramSocket();  
         	mySocket.sendMessage( serverHost, serverPort, "");
			timestamp = mySocket.receiveMessage();// now receive the timestamp
		    mySocket.close( );
       } // end try
	  catch (Exception ex) {
         System.out.println("There is a problem: " + ex);
	    }
       return timestamp;
   } //end getTimeStamp 
} //end class
