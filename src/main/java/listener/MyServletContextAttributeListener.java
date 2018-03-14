package listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

public class MyServletContextAttributeListener implements ServletContextAttributeListener {

	public void attributeAdded(ServletContextAttributeEvent event) {
		System.out.println("ServletContext attributeAdded");
	}

	public void attributeRemoved(ServletContextAttributeEvent event) {
		System.out.println("ServletContext attributeRemoved");
	}

	public void attributeReplaced(ServletContextAttributeEvent event) {
		System.out.println("ServletContext attributeReplaced");
	}


}
