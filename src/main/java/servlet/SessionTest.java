package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * /test/session
 */
public class SessionTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String logout = request.getParameter("action");
		if (logout != null && logout.equals("logout")) {
			HttpSession session = request.getSession(false);
			if (session != null) {
				/*
				 * 使得本地session失效，但并不会删除客户端cookie
				 * 失效后，客户端再次请求会分配新的JSessionId。
				 * 对应到分布式环境时，要删除集中缓存中JSessionId对应的数据.
				 */
				session.invalidate();
			}
			return;
		}
		/*
		 * 从request中获取session，参数为true时，如果req没有对应的session，
		 *（request的cookie中没有JSESSIONID，或JSEESIONID在服务端没有对应的session），就会创建一个新的。
		 * 该方法要在response提交之前创建（因为要将JSESSIONID放入cookie），否则会抛出异常。
		 * 创建session时，会自动将SessionId添加到response的Set-Cookie中，
		 * 该cookie的path是ContextPath(通常是\)，存储方式是内存。
		 */
		HttpSession session = request.getSession(true);
		String id = session.getId();
		System.out.println("sessionId:" + id);
		System.out.println("oneSessionValue:" + session.getAttribute("da"));
		session.setAttribute("da", "xiao");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
