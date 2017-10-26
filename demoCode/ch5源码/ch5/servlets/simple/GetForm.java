// A sample web form processing servlet
// Author: M. Liu

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class GetForm extends HttpServlet {

  public void doGet (HttpServletRequest req, 
     HttpServletResponse res) 
     throws ServletException, IOException
  {
     //The content type of the response body 
     //  should be set first
     res.setContentType("text/html");

     //Get an output stream to write the response body
     ServletOutputStream out = res.getOutputStream();

     out.println("<html>");
     out.println("<head><title>Servlet Response" +
                "</title></head>");
     out.println("<body>");
     out.println("<body bgcolor=\"beige\">");
     out.println("<P>Hello,good afternoon!  </FONT><FONT FACE=" +
                "\"Arial\" SIZE=5 COLOR=\"#ff0000\">");
     //Retreive the value of the parameter named name
     out.println(req.getParameter("name") + "</P></FONT>");
     out.println("<br>");
     out.println("<hr><br>");
     //Retreive the value of the parameter named quest
     out.println("<UL><LI>Quest: " + 
                 req.getParameter("quest") + "</LI>");
     out.println("<LI>Color: " + 
                 req.getParameter("color") + "</LI>");
     out.println("<LI>Swallow Type: " + 
                 req.getParameter("swallow") + "</LI>");
     out.println("<LI>And you said: " + 
                 req.getParameter("text"));
     out.println("</UL><BR>");
     out.println("<HR>");
     out.println("<P><h3>Request processed from: " + 
                 "<font color =\"#00AAFF\">");
     out.println(req.getRemoteHost() + "</FONT></h3></P>"); 
     out.println("</body></html>");
   }
} //end class
