package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class FilterTest1 implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("filter1 init");
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("filter1 doFilter");
		chain.doFilter(request, response);
		System.out.println("filter1 filter finish");
	}
	
	public void destroy() {
		System.out.println("filter1 destory");
	}
}
