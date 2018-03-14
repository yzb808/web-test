package servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * /test/sc
 * 容器中一个应用的所有servlet共用一个servletContext。
 * 1. servletContext能获取xml中使用<context-param>标签初始化的参数
 * 2. 通过对servletContext执行set/getAttribute，获取和存储对所有servlet（所有用户）都可见的属性
 * 3. servletContext支持动态添加servlet，filter，listerner
 */
public class ServletContextTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/**
		 * 不要直接从request中获取servletContext，因为低版本容器不支持
		 * jetty6不支持，jetty8支持
		 */
		ServletContext context = req.getServletContext();
//		ServletContext context = req.getSession().getServletContext();
//		ServletContext context = this.getServletContext();
		Object daxiao = context.getAttribute("daxiao");
		if (daxiao == null) {
			context.setAttribute("daxiao", "da");
		}
		System.out.println("ServletContextAttr:" + daxiao);
		/**
		 * 获取web.xml里用<context-param>标签初始化的参数
		 * servletContext被所有servlet共享
		 */
		System.out.println("InitParameter:" + context.getInitParameter("url"));
		
		/*
		 * getAttribute和setAttribute操作本身是线程安全的，但组合使用不具备原子性，需要用户保障
		 * 
		 */
		System.out.println(context.getAttribute("servlet"));
		context.setAttribute("servlet", this.getClass().getName());
	}
	
}
