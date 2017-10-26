// A sample web form processing servlet
// Author: M. Liu

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Servlet1 extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=gbk");
		PrintWriter out = response.getWriter();
		//ͨ��request��getSession()�����õ�SessionID
		String sessionId=request.getSession().getId();
		//���
		out.print("������һ�λỰ��sessionIdΪ��"+sessionId);
		out.print("<a href='servlet2'>����servlet2</a>");
	} 
} //end class
