// Souce code for Cart servlet invoked when the
// web form cart.html is submitted
// M. Liu
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class Cart extends HttpServlet
{
   public void doPost(HttpServletRequest request, 
     HttpServletResponse response)
     throws ServletException, IOException
   {
 
      /* Retreieve the session object or create
         a new one. */
      HttpSession session = request.getSession(true);

      Integer itemCount = 
        (Integer) session.getValue("itemCount");
      Vector items = 
        (Vector) session.getValue("items");    
      /* If no item has been selected so far, 
         set the count to zero and create a vector.  */
      if (itemCount == null) {
          itemCount = new Integer(0);
          items = new Vector( );
      }

      // It is recommended that you obtain the session
      //  object prior to writing any output.
      PrintWriter out = response.getWriter( );
      response.setContentType("text/html");
      
      /* Retrieve form data */
      Enumeration keys;
      String name, value, prefix;
      int count = itemCount.intValue( );
      keys = request.getParameterNames();
      while (keys.hasMoreElements())
      {
         name = (String)keys.nextElement();
         prefix = name.substring(0,4);
         out.println("name=" + name + " prefix=" + prefix);
         if (prefix.equals("item"))
         {
            // add item to list of items
            value = request.getParameter(name);
            out.println("adding to items:" + 
                        value + " count=" + count);
            items.add(value);
            count++;
 
         }//end if
      } //end while
      itemCount = new Integer(count);
      session.putValue("itemCount", itemCount);
      if (items != null)
         session.putValue("items", items);

      /* Issue a redirect to send the cookies and 
         invoke another servlet to generate a display 
         of the items in the shopping cart */
    
      response.sendRedirect
         ("/examples/servlet/Cart2");    
   } //end doPost

} //end class
