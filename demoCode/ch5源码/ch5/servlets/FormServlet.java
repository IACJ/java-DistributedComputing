// A sample web form processing servlet
// Author: M. Liu

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class FormServlet extends HttpServlet {
	public FormServlet () {  // Destruction of the servlet.  
		super();
	}
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	      String name = request.getParameter("name");//�õ��ϸ�ҳ�洫���Ĳ���name
	      response.setContentType("text/html; charset=UTF-8");//����ҳ�����ΪUTF-8
	      response.getWriter().print("<script>alert('��������û���Ϊ��"+name+"');</script>"); //����һ���Ի���
	}
	public void init() throws ServletException { // Initialization of the servlet
	}
}
