package listener.clazz;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;

public class MyHttpSessionAttr implements HttpSessionBindingListener, HttpSessionActivationListener {

	/*
	 * 该类的对象放入session中时被调用
	 */
	public void valueBound(HttpSessionBindingEvent event) {
		System.out.println("attr add  to session");
	}

	/*
	 * 该类的对象从session中移除时被调用
	 */
	public void valueUnbound(HttpSessionBindingEvent event) {
		System.out.println("attr remove from session");
	}

	/*
	 * 服务器关闭时将会钝化session中数据到磁盘，若该对象位于session中时，调用以下方法
	 */
	public void sessionWillPassivate(HttpSessionEvent se) {
		System.out.println("sessionWillPassivate");
	}

	/*
	 * 服务器启动时，磁盘中保存的session数据会被重新加载，以下方法将会被调用
	 */
	public void sessionDidActivate(HttpSessionEvent se) {
		System.out.println("sessionDidActivate");
	}

}
