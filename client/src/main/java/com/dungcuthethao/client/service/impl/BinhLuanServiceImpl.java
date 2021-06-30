package com.dungcuthethao.client.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dungcuthethao.client.entity.BinhLuan;
import com.dungcuthethao.client.service.IBinhLuanService;

@Service
public class BinhLuanServiceImpl implements IBinhLuanService {
	@Autowired
	private RestTemplate rest;

	@Override
	public void save(BinhLuan binhLuan) {
		rest.postForEntity("binhluan", binhLuan, BinhLuan.class);
		
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<BinhLuan> findBySanPham(Long id) {
		// TODO Auto-generated method stub
		return rest.exchange("binhluan/sanpham/"+id, HttpMethod.GET, null, new ParameterizedTypeReference<List<BinhLuan>>() {
		}).getBody();
	}

}
