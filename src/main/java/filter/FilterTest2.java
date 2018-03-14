package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class FilterTest2 implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("init filter:" + filterConfig.getInitParameter("da"));
		System.out.println("filter2 init");
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("filter2 doFilter");
		chain.doFilter(request, response);
		System.out.println("filter2 filter finish");
	}
	
	public void destroy() {
		System.out.println("filter2 destory");
	}


}
