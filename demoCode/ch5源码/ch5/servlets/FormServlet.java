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
	      String name = request.getParameter("name");//得到上个页面传来的参数name
	      response.setContentType("text/html; charset=UTF-8");//设置页面编码为UTF-8
	      response.getWriter().print("<script>alert('您输入的用户名为："+name+"');</script>"); //弹出一个对话框
	}
	public void init() throws ServletException { // Initialization of the servlet
	}
}
