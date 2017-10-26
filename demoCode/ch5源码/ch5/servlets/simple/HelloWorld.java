//Import Servlet Libraries
import javax.servlet.*;
import javax.servlet.http.*;

//Import Java Libraries
import java.io.*;

public class HelloWorld extends HttpServlet
{

   public void doGet(HttpServletRequest req, HttpServletResponse res)
          throws ServletException, IOException
   {

      res.setContentType("text/html");
      PrintWriter out = res.getWriter();

   
      out.println("</HEAD>");
      out.println("<BODY>");
      out.println("<CENTER><H1>Hello World!</H1></CENTER>");
      out.println("</BODY>");
      out.println("</HTML>");

      out.close();

   }
}
