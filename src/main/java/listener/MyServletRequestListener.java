package listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class MyServletRequestListener implements ServletRequestListener {

	public void requestDestroyed(ServletRequestEvent sre) {
		System.out.println("request destory");
	}

	public void requestInitialized(ServletRequestEvent sre) {
		System.out.println("request create");
	}

}
