//Import Servlet Libraries
import javax.servlet.*;
import javax.servlet.http.*;

//Import Java Libraries
import java.io.*;

public class SimpleServlet extends HttpServlet
{

  public void doGet (HttpServletRequest request, HttpServletResponse response)   throws ServletException, IOException { 
       PrintWriter out; 
       String title = "Simple Servlet Output"; 
       // set content type and other response header fields first 
       response.setContentType("text/html");
      // then write the data of the response 
      out = response.getWriter(); 
      out.println("<HTML><HEAD><TITLE>");
      out.println(title); 
      out.println("</TITLE></HEAD><BODY>"); 
      out.println("<H1>" + title +   "</H1>"); 
      out.println("<P>This is output from SimpleServlet."); 
      out.println("<P>Welcome!"); 
      out.println("<P>It is sunny!"); 
      out.println("<P>Today is Monday!!!!");
      out.println("</BODY></HTML>"); 
      out.close();
      } //end method
      
      public void doPost (HttpServletRequest request, HttpServletResponse response)   throws ServletException, IOException { 
       PrintWriter out; 
       String title = "Simple Servlet Output"; 
       // set content type and other response header fields first 
       response.setContentType("text/html");
      // then write the data of the response 
      out = response.getWriter(); 
      out.println("<HTML><HEAD><TITLE>");
      out.println(title); 
      out.println("</TITLE></HEAD><BODY>"); 
      out.println("<H1>" + title +   "</H1>"); 
      out.println("<P>This is output from SimpleServlet."); 
      out.println("<P>Welcome!"); 
      out.println("<P>hello!"); 
      out.println("</BODY></HTML>"); 
      out.close();
      } //end method
}
