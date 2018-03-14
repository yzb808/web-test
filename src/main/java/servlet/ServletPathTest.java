package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 探究pathInfo的值
 * @author yzb808
 *
 */
public class ServletPathTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*
	 * servlet创建
	 */
	@Override
	public void init() throws ServletException {
		super.init();
	}

	/**
	 * ContextPath是容器上下文名称，用于区分容器中的多个实例，应用请求的url会截取其中contextPath的内容，再做servlet映射
	 * ServletPath是servlet请求中path匹配到的部分
	 * pathInfo中存放被正则匹配到的部分
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("servlet2 get");
		System.out.println("ContextPath:" + req.getContextPath());
		System.out.println("ServletPath:" + req.getServletPath());
		System.out.println("PathInfo:" + req.getPathInfo());
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
}
