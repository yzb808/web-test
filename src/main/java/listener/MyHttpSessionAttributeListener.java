package listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class MyHttpSessionAttributeListener implements HttpSessionAttributeListener {

	public void attributeAdded(HttpSessionBindingEvent event) {
		System.out.println("HttpSessionAttribute attributeAdded");
	}

	public void attributeRemoved(HttpSessionBindingEvent event) {
		System.out.println("HttpSessionAttribute attributeRemoved");		
	}

	public void attributeReplaced(HttpSessionBindingEvent event) {
		System.out.println("HttpSessionAttribute attributeReplaced");		
	}


}
