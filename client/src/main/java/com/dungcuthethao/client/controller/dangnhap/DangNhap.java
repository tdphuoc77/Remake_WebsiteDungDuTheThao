package com.dungcuthethao.client.controller.dangnhap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DangNhap {
	@RequestMapping(value = "/dang-nhap", method = RequestMethod.GET)
	public String index() {
		return "user/dangnhap";
	}
	
	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public String accessDenied() {
		return "redirect:/dang-nhap?accessDenied";
	}
	
	/*
	 * @RequestMapping(value = "/j_spring_security_login",method =
	 * RequestMethod.GET) public String dangnhap (@RequestParam String username) {
	 * System.out.println(SecurityUtils.getPrincipal()+"CHECKKK"); String url = "";
	 * List<String> roles = SecurityUtils.getAuthorities();
	 * System.out.println(roles); if (roles.contains("ROLE_QUANTRI")) { url =
	 * "/quan-tri/trang-chu"; } else if (roles.contains("ROLE_NGUOIDUNG")) { url =
	 * "/trang-chu"; } System.out.println(url); return "redirect:"+url; }
	 */
	
}
