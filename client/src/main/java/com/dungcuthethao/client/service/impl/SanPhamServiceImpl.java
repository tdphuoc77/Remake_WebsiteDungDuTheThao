package com.dungcuthethao.client.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dungcuthethao.client.entity.SanPham;
import com.dungcuthethao.client.service.ISanPhamService;

@Service
public class SanPhamServiceImpl implements ISanPhamService {
	
	@Autowired
	private RestTemplate rest;

	@Override
	public List<SanPham> findByTen(String ten, int page, int limit) {
		// TODO Auto-generated method stub
		return rest.exchange("sanpham/search?page="+page+"&&limit="+limit, HttpMethod.GET, null, new ParameterizedTypeReference<List<SanPham>>() {
		}).getBody();
	}


	@Override
	public void save(SanPham sanPham) {
		rest.postForEntity("sanpham", sanPham, SanPham.class);
		
	}

	@Override
	public SanPham findById(Long id) {
		// TODO Auto-generated method stub
		return rest.getForObject("sanpham/"+id, SanPham.class);
	}

	@Override
	public void updateSanPham(SanPham sanPham) {
		rest.put("sampham", sanPham);
		
	}

	@Override
	public List<SanPham> findTop3SanPhamBySoLuotXem() {
		// TODO Auto-generated method stub
		return rest.exchange("sanpham/top3soluotxem", HttpMethod.GET, null, new ParameterizedTypeReference<List<SanPham>>() {
		}).getBody();
	}

	@Override
	public List<SanPham> findDSSanPhamNoiBat() {
		// TODO Auto-generated method stub
		return rest.exchange("sanpham/top10noibat", HttpMethod.GET, null, new ParameterizedTypeReference<List<SanPham>>() {
		}).getBody();
	}

	@Override
	public List<SanPham> findTop3SanPhamGiamGiaNhieuNhat() {
		return rest.exchange("sanpham/top3sanphangiamgia", HttpMethod.GET, null, new ParameterizedTypeReference<List<SanPham>>() {
		}).getBody();
	}

	@Override
	public Long getTotalItem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTrangThaiSanPham(Long id, boolean tt) {
		// TODO Auto-generated method stub
		
	}

}
