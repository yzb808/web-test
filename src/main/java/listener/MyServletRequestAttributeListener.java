package listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;

public class MyServletRequestAttributeListener implements ServletRequestAttributeListener {

	public void attributeAdded(ServletRequestAttributeEvent event) {
		System.out.println("ServletRequestAttribute attributeAdded");
	}

	public void attributeRemoved(ServletRequestAttributeEvent event) {
		System.out.println("ServletRequestAttribute attributeRemoved");		
	}

	public void attributeReplaced(ServletRequestAttributeEvent event) {
		System.out.println("ServletRequestAttribute attributeReplaced");		
	}


}
