package com.dungcuthethao.client.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dungcuthethao.client.entity.AnhSanPham;
import com.dungcuthethao.client.service.IAnhSanPhamService;

@Service
public class AnhSanPhamImpl implements IAnhSanPhamService {
	
	@Autowired
	private RestTemplate rest;

	@Override
	public void saveAnhSP(AnhSanPham anhSanPham) {
		
		
	}

	@Override
	public void updateAnhSP(AnhSanPham anhSanPham) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAnhSP(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<AnhSanPham> getBysp(Long id) {
		// TODO Auto-generated method stub
		return rest.exchange("anhsanpham/sanpham/"+id, HttpMethod.GET, null, new ParameterizedTypeReference<List<AnhSanPham>>() {
		}).getBody();
	}

}
