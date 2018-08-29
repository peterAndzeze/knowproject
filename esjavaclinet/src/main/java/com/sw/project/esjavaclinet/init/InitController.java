package com.sw.project.esjavaclinet.init;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;

@Controller
public class InitController {
	private static Logger logger = LoggerFactory.getLogger(InitController.class);

	@RequestMapping("/")
	public String start(Model model) {
		System.out.println("初始化");
		return "index";
	}

	@RequestMapping("login")
	public String login(Model model, HttpServletRequest request, String userName, String password) {
		if (null == userName || "".equals(userName)) {
			return "index";
		}
		if (userName.equals("admin") && userName.equals(password)) {
			model.addAttribute("userName", userName);
			request.getSession().setAttribute("session_user", userName + password);
			request.getSession().setMaxInactiveInterval(3600);
			return "main";
		} else {
			return "index";
		}
	}



}
