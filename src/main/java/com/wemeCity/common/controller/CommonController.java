package com.wemeCity.common.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.wemeCity.admin.sysUser.model.SysUser;
import com.wemeCity.common.utils.Constants;

@Controller
public class CommonController extends BaseController {

	@GetMapping("/")
	public String root() throws Exception {
		return "login";
	}

	@GetMapping("/login")
	public String login() throws Exception {
		return "login";
	}

	@GetMapping("/logOff")
	public String logOff(HttpSession session) throws Exception {
		session.removeAttribute(Constants.CURRENT_USER);
		return "redirect:login";
	}

	@GetMapping("/frame")
	public String frame(HttpSession session, ModelMap modelMap) throws Exception {
		SysUser sysUser = getCurUser(session);
		if (sysUser == null) {
			return "redirect:login";
		}
		modelMap.put("sysUser", sysUser);
		return "frame";
	}

	@GetMapping("/welcome")
	public String welcome(HttpSession session) throws Exception {
		return "welcome";
	}
}
