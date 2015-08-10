package com.skg.luohong.app.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Hello world!
 *
 */
@Controller
public class App {
	
	@RequestMapping("/hello.do")
	@ResponseBody
	public String helloWorld(){
		return "hello world";
	}
	
}
