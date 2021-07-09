package com.dungcuthethao.client.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dungcuthethao.client.entity.NguoiDung;
import com.dungcuthethao.client.service.INguoiDungService;

@Service
public class NguoiDungServiceImpl implements INguoiDungService {
	@Autowired
	private RestTemplate rest;
	@Override
	public void saveNguoiDung(NguoiDung nguoiDung) {
		rest.postForEntity("nguoidung", nguoiDung, NguoiDung.class);
		
	}

	@Override
	public void UpdateNguoiDung(NguoiDung nguoiDung) {
		rest.put("nguoidung", nguoiDung);
		
	}

	@Override
	public NguoiDung findById(Long id) {
		// TODO Auto-generated method stub
		return rest.getForObject("nguoidung/"+id, NguoiDung.class);
	}

	@Override
	public List<NguoiDung> findByTenAndHo(String ten, String ho) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NguoiDung findOneByTenDangNhap(String tenDangNhap) {
		// TODO Auto-generated method stub
		return rest.getForObject("nguoidung/tendangnhap?ten="+tenDangNhap, NguoiDung.class);
	}

	@Override
	public Long getTotalItem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTrangThaiNguoiDung(Long id, boolean tt) {
		
		
	}

	@Override
	public List<NguoiDung> findAll() {
		// TODO Auto-generated method stub
		return rest.exchange("nguoidung", HttpMethod.GET, null, new ParameterizedTypeReference<List<NguoiDung>>() {
		}).getBody();
	}

	@Override
	public List<NguoiDung> findAllAndPaging(int page, int limit) {
		return rest.exchange("nguoidung/phantrang?page="+page+"&&limit="+limit, HttpMethod.GET, null, new ParameterizedTypeReference<List<NguoiDung>>() {
		}).getBody();
	}

}
