// Servlet to view what is in the shopping cart (as recorded by
//   the use a session object in the Cart servlet
// M. Liu, based on various sources

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class Cart2 extends HttpServlet{

   /* View items in shopping cart */
   public void doGet(HttpServletRequest req, 
     HttpServletResponse res) 
     throws ServletException, IOException
   {
 
      // Retreieve the session object, if any
      HttpSession session = req.getSession(false);
      Integer itemCount;
      Vector items = null;
      if (session == null)
      {
          //no session object has been created
          itemCount = new Integer(0);
      }
      else
      {
        itemCount = 
          (Integer) session.getValue("itemCount");
        items = 
          (Vector) session.getValue("items"); 
      }   
      // It is recommended that you obtain the session object
      // prior to writing any output.
      PrintWriter out = res.getWriter( );
      res.setContentType("text/html");

      out.println("<html>");
      out.println("<head><title>Servlet Response" +
                  "</title></head>");
      out.println("<body>");
      out.println("<body bgcolor=\"beige\">");
      out.println
        ("Contents of your shopping cart " +
          " using session object<UL>");

      int count = itemCount.intValue( );
      /* Retrieve the items from the session object*/
      for (int i = 0; i < count; i++)
        out.println("<LI>" + items.get(i));
      
      out.println("</UL>");
      out.println("<HR>");
      out.println("</body></html>");

   } // end doGet

} // end Cart2
