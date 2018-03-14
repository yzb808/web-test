package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * /test/error
 */
public class ServletSendErrorTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * 这里中文会乱码， 这时contentType也无效
		 * 因为error页面默认使用ErrorPageErrorHandler处理，该类的handle方法强制
		 * 设置Content-Type是'text/html; charset=iso-8859-1'
		 */
		resp.setContentType("text/html; charset=utf-8");
		resp.sendError(501, "no permission!");
	}
	
	/**
	 * 对jetty容器而言，request中host是必须的
	 */
	public static void main(String[] args) throws Exception {
		Socket socket = new Socket("localhost", 8080);
		PrintStream out = new PrintStream (socket.getOutputStream());
		out.println("GET /test/error HTTP/1.1");
		out.println("Host: localhost:8080");
		out.println();
		out.flush();
		InputStream in = socket.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
		String line = null;
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
		}
		out.close();
		in.close();
		socket.close();
	}
}
