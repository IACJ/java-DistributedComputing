// Servlet to view what is in the shopping cart (as recorded by
//   the use of cookies in the Cart servlet
// M. Liu, based on various sources

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class Cart2 extends HttpServlet
{
   /* View items in shopping cart */
   public void doGet(HttpServletRequest req, 
     HttpServletResponse res) 
     throws ServletException, IOException
   {
      res.setContentType("text/html");
      ServletOutputStream out = res.getOutputStream();
      out.println("<html>");
      out.println("<head><title>Servlet Response" +
                  "</title></head>");
      out.println("<body>");
      out.println("<body bgcolor=\"beige\">");
      out.println("Contents of your shopping cart<UL>");

      /* Retrieve the cookies */
      Cookie cookies[];

      cookies = req.getCookies();
      if (cookies != null){

         for (int i = 0; i < cookies.length; i++){

            /* Note: It is important to identify the cookies
               by name, as there may be other cookies in use
               for this site. */
            if (cookies[i].getName().startsWith("item")){

               out.println("<LI>" + cookies[i].getName() +
                  "\t" +  cookies[i].getValue());
            }
         } // end for
      } // end if

      out.println("</UL>");
      out.println("<HR>");
      out.println("</body></html>");

   } // end doGet

} // end Cart2
