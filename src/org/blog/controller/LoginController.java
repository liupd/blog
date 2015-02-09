package org.blog.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

	@RequestMapping(value = "/login.do")
	@ResponseBody
	public Object login(HttpServletRequest request, String username,
			String password) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		if(username.equals("yangfan") && password.equals("yangfan")){
			request.getSession().setAttribute("username", username);
			result.put("success", new Boolean(true));
			result.put("message", "登陆成功！");
		}else{
			result.put("success", new Boolean(false));
			result.put("message", "登陆失败，密码错误！");
		}
		return result;
	}
}
