package com.dungcuthethao.client.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dungcuthethao.client.entity.HoaDon;
import com.dungcuthethao.client.service.IHoaDonService;

@Service
public class HoaDonServiceImpl implements IHoaDonService {
	@Autowired
	private RestTemplate rest;

	@Override
	public void saveHoaDon(HoaDon hoaDon) {
		rest.postForEntity("hoadon", hoaDon, HoaDon.class);
		
	}

	@Override
	public HoaDon findById(Long id) {
		// TODO Auto-generated method stub
		return rest.getForObject("/hoadon/"+id, HoaDon.class);
	}

	@Override
	public Long getTotalItem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteHoaDon(Long id) {
		rest.delete("hoadon/"+id);
		
	}



	@Override
	public void updateHoaDon(HoaDon hoaDon) {
		rest.put("hoadon", HoaDon.class);
		
	}



	@Override
	public List<HoaDon> findListHoaDonDaGiao() {
		// TODO Auto-generated method stub
		return rest.exchange("hoadon/trangthai/dagiao", HttpMethod.GET, null, new ParameterizedTypeReference<List<HoaDon>>() {
		}).getBody();
	}

	@Override
	public List<HoaDon> findListHoaChuaGiao() {
		// TODO Auto-generated method stub
		return rest.exchange("hoadon/trangthai/chuagiao", HttpMethod.GET, null, new ParameterizedTypeReference<List<HoaDon>>() {
		}).getBody();
	}

	@Override
	public List<HoaDon> getByNguoiDungNhan(Long idND) {
		// TODO Auto-generated method stub
		return rest.exchange("hoadon/nguoidung/danhan/"+idND, HttpMethod.GET, null, new ParameterizedTypeReference<List<HoaDon>>() {
		}).getBody();
	}

	@Override
	public List<HoaDon> getByNguoiDungChuaNhan(Long idND) {
		// TODO Auto-generated method stub
		return rest.exchange("hoadon/nguoidung/chuanhan/"+idND, HttpMethod.GET, null, new ParameterizedTypeReference<List<HoaDon>>() {
		}).getBody();
	}

}
