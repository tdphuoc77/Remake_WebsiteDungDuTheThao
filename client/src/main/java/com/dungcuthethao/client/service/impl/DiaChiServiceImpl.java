package com.dungcuthethao.client.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dungcuthethao.client.entity.DiaChi;
import com.dungcuthethao.client.service.IDiaChiService;

@Service
public class DiaChiServiceImpl implements IDiaChiService{
	
	@Autowired
	private RestTemplate rest;

	@Override
	public void saveDiaChi(DiaChi diaChi) {
		rest.postForEntity("diachi", diaChi, DiaChi.class);
	}

	@Override
	public List<DiaChi> findByNguoiDungID(Long id) {
		return rest.exchange("diachi/nguoidung/"+id, HttpMethod.GET, null, new ParameterizedTypeReference<List<DiaChi>>() {
		}).getBody();
	}

}
