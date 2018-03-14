package service;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component
public class TestService2 {

	@Resource
	private TestService testService;

}
