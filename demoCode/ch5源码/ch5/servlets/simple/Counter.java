// An example which illustrates that a servlet is persistent.

// M. Liu

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Counter extends HttpServlet {

   int counter = 0;

   public void doGet(HttpServletRequest request, 
					HttpServletResponse response) 
          throws ServletException, IOException {
      response.setContentType("text/plain");
      PrintWriter output = response.getWriter();

      counter++;

      output.println("This servlet has been" + 
				" accessed " + counter + " times.");
   } //end doGet
} //end class
