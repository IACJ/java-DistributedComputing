import javax.servlet.*;
// Source code for Cart servlet invoked when the
// web form cart.html is submitted
// M. Liu

import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class Cart extends HttpServlet{

   public void doPost(HttpServletRequest request, 
     HttpServletResponse response)
     throws ServletException, IOException{

      response.setContentType("text/html");
      ServletOutputStream out = response.getOutputStream();
      out.println("<html>");
      out.println("<head><title>Servlet Response" +
                  "</title></head>");
      out.println("<body>");
      Cookie c;

      /* Retrieve form data */
      Enumeration keys;
      String  name, value, prefix;
      keys = request.getParameterNames();
      while (keys.hasMoreElements()){

         name = (String)keys.nextElement();
         prefix = name.substring(0,4);
         
         if (prefix.equals("item")){   
         // This test is necessary to eliminate
         // input fields that are not items.

            /* Retrieve the parameter value */
            value = request.getParameter(name);
            /* Create a cookie */
            out.println("<H4>Setting cookie: " + name +
                        " " + value + "</H4>");
            c = new Cookie(name, value);
                   
            /* Set it to expire in 1 day */
            /* c.setMaxAge(1*24*60*60);  */
            response.addCookie(c);
         }//end if
      } //end while
      out.println("</body></html>");

      /* Issue a redirect to send the cookies and 
         invoke another servlet to generate a display 
         of the items in the shopping cart */
      //response.sendRedirect("Cart2");

   } //end doPost

} //end class
