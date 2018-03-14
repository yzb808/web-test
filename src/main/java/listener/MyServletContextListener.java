package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("servletContext start");
	}
	
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("servletDestory start");
	}
}
