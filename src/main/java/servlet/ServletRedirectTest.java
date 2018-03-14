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

public class ServletRedirectTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * sendRedirect实际是做如下逻辑
		 * HTTP/1.1 302 Found
		 * Location: http://www.baidu.com
		 */
		resp.sendRedirect("http://www.baidu.com");
	}
	
	/**
	 * 对jetty容器而言，request中host是必须的
	 * response中分配了JSESSIONID：
	 * 	Set-Cookie: JSESSIONID=hrdciq4j6t0i1oqtk92lz5v1u;Path=/test
	 */
	public static void main(String[] args) throws Exception {
		Socket socket = new Socket("localhost", 8080);
		PrintStream out = new PrintStream (socket.getOutputStream());
		out.println("GET /test/redirect HTTP/1.1");
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
