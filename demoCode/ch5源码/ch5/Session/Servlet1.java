// A sample web form processing servlet
// Author: M. Liu

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Servlet1 extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=gbk");
		PrintWriter out = response.getWriter();
		//通过request的getSession()方法得到SessionID
		String sessionId=request.getSession().getId();
		//输出
		out.print("产生了一次会话，sessionId为："+sessionId);
		out.print("<a href='servlet2'>访问servlet2</a>");
	} 
} //end class
