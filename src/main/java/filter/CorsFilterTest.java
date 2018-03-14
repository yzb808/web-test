package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/*
 * 支持跨域请求
 * 注意跨域访问/test不能进入该filter，原因还不明白
 */
public class CorsFilterTest implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("filter1 init");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse) response;
		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
		res.setHeader("Access-Control-Max-Age", "3600"); // 设置预检请求过期时间
		res.setHeader("Access-Control-Allow-Headers",
				"Origin, X-Requested-With, Content-Type, Accept, client_id, uuid, Authorization");
		res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // 支持HTTP1.1.
		res.setHeader("Pragma", "no-cache"); // 支持HTTP1.0.response.setHeader("Expires","0");
		chain.doFilter(request, res);
	}

	public void destroy() {
		System.out.println("filter1 destory");
	}
}
