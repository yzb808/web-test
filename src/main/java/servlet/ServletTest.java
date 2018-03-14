package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * /test/sTest/a/a/a
 */
@SuppressWarnings("unused")
public class ServletTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*
	 * servlet创建，知识点：
	 * servlet创建时会调用初始化函数，初始化函数有两个 init()和init(ServletConfig)
	 * 1. 容器实际上是调用init(ServletConfig)函数，该函数会再次调用init()方法
	 * 2. 如果重构init(ServletConfig)方法切不调用super.init方法，则init()方法不会被调用
	 * 且servletConfig对象不会被注入到HttpServlet对象中，因此尽量不要初始化init(ServletConfig)方法
	 * 通常在servlet中通过this.getServletConfig()方法获得servletConfig对象
	 * 3. ServletConfig功能有两个 a.获取Servlet基本信息； b.获取web.xml中使用<init-param>标签为Servlet初始化的参数
	 */
	@Override
	public void init() throws ServletException {
		System.out.println("servlet init");
	}
	
	/*
	 * 完全重构init(ServletConfig)方法会造成init()方法不被调用
	 * 在web.xml中使用<init-param>标签初始化参数，并通过ServletConfig对象取出
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("InitParameter:" + config.getInitParameter("duo"));
		System.out.println("servlet init config");
		ServletContext context = config.getServletContext();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * value是String[]类型是因为传入参数可能是t1=1&t1=2&t2=3
		 */
		Map<String, String[]> params = req.getParameterMap();
		System.out.println("servlet-" + params.size());
		/*
		 * 当确定只有一个t1时使用getParameter
		 * 有多个t1时使用getParameterValues
		 * 多个t1情况下使用getParameter会返回第一个value
		 * 注意value可能为空字符串
		 */
		String value = req.getParameter("t4");
		String[] values = req.getParameterValues("t1");
		Enumeration<String> enumeration = req.getParameterNames();
		while (enumeration.hasMoreElements()) {
			System.out.println(enumeration.nextElement());
		}
		System.out.println(enumeration);
		
		System.out.println(req.getRequestURI());
		
		Enumeration<String> attrNames = req.getAttributeNames();
		while (attrNames.hasMoreElements()) {
			System.out.println(attrNames.nextElement());
		}
		
		Enumeration<String> headNames = req.getHeaderNames();
		while (headNames.hasMoreElements()) {
			System.out.println(headNames.nextElement());
		}
		
		System.out.println(req.getContextPath());
		System.out.println(req.getServletPath());
		System.out.println(req.getPathInfo());
		
		/**
		 * headName = Cookie的head会被作为cookie填充到req中
		 */
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				System.out.println("cookie:" + cookies[i].getName());
			}
		}
		
		System.out.println("encoding:" + req.getCharacterEncoding());
		
		/**
		 * contextPath作为最高级别的命名空间被使用，通常情况下是空。
		 * 应用请求的url会截取其中contextPath的内容，再做servlet映射
		 * ServletPath是servlet请求的path
		 */
		System.out.println("ContextPath:" + req.getContextPath());
		System.out.println("ServletPath:" + req.getServletPath());
		System.out.println("PathInfo:" + req.getPathInfo());
		
		System.out.println("RemoteAddr:" + req.getRemoteAddr());
		
		/**
		 * 对应Accept-Language
		 */
		System.out.println("Accept-Language:" + req.getLocale());
		/**
		 * 对应CharacterEncoding
		 */
		System.out.println("Content-Type:" + req.getCharacterEncoding());
		
		System.out.println("get");
		
		/*
		 * attribute在该请求在容器流转过程中有效,请求处理完成后,req中的attribute会被清除
		 */
		System.out.println("attribute:" + req.getAttribute("da"));
		req.setAttribute("da", "xiao");
		System.out.println("attribute:" + req.getAttribute("da"));
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * post在Content-Type=application/x-www-form-urlencoded时，body体中的数据会被解析到params中
		 */
		Map<String, String[]> params = req.getParameterMap();
		System.out.println(params);
		
		/**
		 * 对应Content-Type: application/json;charset=UTF-8
		 * 其中application/json是ContentType, UTF-8对应CharacterEncoding
		 * CharacterEncoding缺省状态是ISO-8859-1，但此时getCharacterEncoding返回null，
		 * 表示用户没有指定编码。
		 * 因此在一些encoding缺省的场景下，为了避免乱码，服务端第一个filter就要为request指定encoding。
		 */
		System.out.println("encoding:" + req.getCharacterEncoding());
		System.out.println("Content-Type:" + req.getContentType());
		
		/*
		 * 通过in获取requst body里的数据
		 */
		InputStream in = req.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in, req.getCharacterEncoding() == null ? "utf-8" : req.getCharacterEncoding()));
		String line = null;
		while ((line = reader.readLine()) != null) {
			System.out.println("line:" + line);
		}
		in.close();
		
		/*
		 * 通过向resp的out写入实现向http请求方返回结果
		 */
		String responseData = "大小多少";
		resp.setContentLength(responseData.getBytes("gbk").length);
		resp.setContentType("test/plain;charset=gbk");
		OutputStream output = resp.getOutputStream();
		output.write(responseData.getBytes("gbk"));
		output.flush();
		output.close();
		
		System.out.println("post");
	}
}
