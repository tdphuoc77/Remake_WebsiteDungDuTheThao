package com.dungcuthethao.client.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dungcuthethao.client.entity.LoaiNguoiDung;
import com.dungcuthethao.client.service.ILoaiNguoiDungService;

@Service
public class LoaiNguoiDungServiceImpl implements ILoaiNguoiDungService {
	
	@Autowired
	private RestTemplate rest;
	@Override
	public LoaiNguoiDung findByID(Long id) {
		// TODO Auto-generated method stub
		return rest.getForObject("loainguoidung/"+id, LoaiNguoiDung.class);
	}

	@Override
	public LoaiNguoiDung findByTenLoaiNguoiDung(String tenLoai) {
		// TODO Auto-generated method stub
		return rest.getForObject("loainguoidung/ten?ten="+tenLoai, LoaiNguoiDung.class);
	}

}
