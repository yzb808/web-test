package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * /test/cookie
 */
public class CookieTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * 展示当前request中携带的cookies
		 */
		System.out.println(request.getCookies());
		/*
		 * action=del时删除cookie，被删除cookie maxage=0，另外cookie的其它属性也必须对的上
		 * 例如path属性，否则删除不成功
		 */
		String action = request.getParameter("action");
		if (action != null && action.equals("del")) {
			Cookie cookie = new Cookie("da", System.currentTimeMillis() % 100 + "");
			cookie.setMaxAge(0);
			cookie.setPath(request.getContextPath());
			response.addCookie(cookie);
		} else {
			Cookie cookie = new Cookie("da", "1");
			cookie.setPath(request.getContextPath());
			cookie.setMaxAge(60);	// 有效时间60s
			cookie.setHttpOnly(true);
			response.addCookie(cookie);
			Cookie cookie1 = new Cookie("da", "2");
			cookie1.setPath(request.getContextPath());
			cookie1.setMaxAge(60);	// 有效时间60s
			cookie1.setHttpOnly(true);
			response.addCookie(cookie1);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
