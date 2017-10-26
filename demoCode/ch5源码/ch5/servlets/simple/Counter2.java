// A servlet which maintains a counter for
//  the number of times that it has been 
//  accessed since its loading.
// M. Liu

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Counter2 extends HttpServlet {

   public int counter = 0;

   public void doGet(HttpServletRequest request, 
					HttpServletResponse response) 
         throws ServletException, IOException {
      response.setContentType("text/plain");
      PrintWriter output = response.getWriter();
      increment(output);
   } //end doGet

   private synchronized void increment(PrintWriter output){
      output.println("This servlet has been" + 
				" accessed " + counter + " times.");
      counter++;
   } //end increment

} //end class
