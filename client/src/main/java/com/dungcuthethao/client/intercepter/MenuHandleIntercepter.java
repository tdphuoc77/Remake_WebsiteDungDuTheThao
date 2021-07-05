package com.dungcuthethao.client.intercepter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dungcuthethao.client.entity.DanhMuc;
import com.dungcuthethao.client.service.impl.DanhMucServiceImpl;
@SuppressWarnings("deprecation")
@Component
public class MenuHandleIntercepter extends HandlerInterceptorAdapter {

	@Autowired
	DanhMucServiceImpl danhMucServiceImpl;
	

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		List<DanhMuc> dsDanhMuc = danhMucServiceImpl.findAllDanhMucCha();
		Map<DanhMuc, List<DanhMuc>> danhMucMap = new HashMap<DanhMuc, List<DanhMuc>>();
		for (DanhMuc danhMuc : dsDanhMuc) {
			List<DanhMuc> dsDanhMucCon = danhMucServiceImpl.findAllDanhMucConById(danhMuc.getId());
			danhMucMap.put(danhMuc, dsDanhMucCon);
		}
		request.setAttribute("dsDanhMuc", danhMucMap);
		return true;
	}

}
