package service;

import javax.annotation.PostConstruct;

/**
 * springCtx初始化先于Servlet和Filter
 * @author yzb808
 *
 */
public class TestService {

	private String name;
	
	public TestService() {
		System.out.println("TestService:" + name);
	}
	
	/**
	 * bean需要被引用才会执行PostConstruct方法，否则只执行构造方法
	 */
	@PostConstruct
	public void init() {
		System.out.println("TestService:" + name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
