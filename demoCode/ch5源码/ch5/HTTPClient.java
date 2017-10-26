// MyStreamSocket is a Java class presented in Chapter 4.
// import MyStreamSocket;
import java.net.*;
import java.io.*;

public class HTTPClient {

// An application which communicates with a HTTP
// server to retrieve the text contents of a web page.
// These command line arguments are expected, in order: 
//    <host name of the HTTP server>
//    <port number of the HTTP server>
//    <full path to a web document on the server host>

   public static void main(String[] args) {
      if (args.length != 3)
         System.out.println
            ("This program requires 3 command line arguments");
      else {
         try {
  		      InetAddress host = 
                InetAddress.getByName(args[0]);
  		      int port = Integer.parseInt(args[1].trim());
            String fileName = args[2].trim();
           String request = "GET " + fileName + " HTTP/1.0\r\n";
          // String request = "PUT /"+ fileName + " HTTP/1.0\r\n";
            
         //	request+="Accept: */*/r/n"+"Connection: Keep-Alive\r\n"+"Host: localhost\r\n"+"Content-Length: 94\r\n"
         // +"User-Agent: Generic\r\n\n";
         // request+="<html>"+"<head>"+"<title>My web page </title>"+"</head>"+
          //  "<body>"+"good afternoon, Hello world!"+"</body>"+"</html>";
   
            
           // System.out.println(request);
         	MyStreamSocket mySocket = new MyStreamSocket(host, port);                             
            /**/  System.out.println("Connection made");
            mySocket.sendMessage(request);
	         // now receive the response from the HTTP server
            String response;         
            response = mySocket.receiveMessage();
            // read and display one line at a time
            while (response != null) {
              System.out.println(response);
              response = mySocket.receiveMessage();
            }
         } // end try
         catch (Exception ex) {
            System.out.println("ERROR : " + ex) ;
	         ex.printStackTrace(System.out);
	      }  // end catch
      }// end else
   }// end main 
} //end class
