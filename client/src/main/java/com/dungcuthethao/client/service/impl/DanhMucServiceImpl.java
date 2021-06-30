package com.dungcuthethao.client.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dungcuthethao.client.entity.DanhMuc;
import com.dungcuthethao.client.service.IDanhMucService;

@Service
public class DanhMucServiceImpl implements IDanhMucService{
	
	@Autowired
	private RestTemplate rest;

	@Override
	public List<DanhMuc> findAll() {
		// TODO Auto-generated method stub
		return rest.exchange("danhmuc", HttpMethod.GET, null, new ParameterizedTypeReference<List<DanhMuc>>() {
		}).getBody();
	}

	@Override
	public DanhMuc findByTen(String ten) {
		// TODO Auto-generated method stub
		return rest.getForObject("danhmuc/ten?ten="+ten, DanhMuc.class);
	}

	@Override
	public List<DanhMuc> findAllDanhMucConById(Long id) {
		// TODO Auto-generated method stub
		return rest.exchange("danhmuc/danhmuccha/"+id, HttpMethod.GET, null, new ParameterizedTypeReference<List<DanhMuc>>() {
		}).getBody();
	}

	@Override
	public List<DanhMuc> findAllDanhMucCha() {
		// TODO Auto-generated method stub
		return rest.exchange("danhmuc/danhmuccha", HttpMethod.GET, null, new ParameterizedTypeReference<List<DanhMuc>>() {
		}).getBody();
	}

	@Override
	public void updateDanhMuc(DanhMuc danhMuc) {
		rest.put("danhmuc", danhMuc);
		
	}

	@Override
	public void themDanhMuc(DanhMuc danhMuc) {
		rest.postForEntity("danhmuc", danhMuc, DanhMuc.class);
		
	}

	@Override
	public DanhMuc findOne(Long id) {
		// TODO Auto-generated method stub
		return rest.getForObject("danhmuc/"+id, DanhMuc.class);
	}

	@Override
	public List<DanhMuc> findAllDanhMucCon() {
		// TODO Auto-generated method stub
		return rest.exchange("danhmuc/danhmuccon", HttpMethod.GET, null, new ParameterizedTypeReference<List<DanhMuc>>() {
		}).getBody();
	}

	@Override
	public List<DanhMuc> findAllAndPaging(int page, int limit) {
		// TODO Auto-generated method stub
		return rest.exchange("danhmuc/phantrang?page="+page+"&&limit="+limit, HttpMethod.GET, null, new ParameterizedTypeReference<List<DanhMuc>>() {
		}).getBody();
	}

}
