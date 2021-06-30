package com.dungcuthethao.client.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dungcuthethao.client.entity.ChiTietHoaDon;
import com.dungcuthethao.client.service.IChiTietHoaDonService;

@Service
public class ChiTietHoaDonServiceImpl implements IChiTietHoaDonService {
	
	@Autowired
	private RestTemplate rest;

	@Override
	public void saveCTHD(ChiTietHoaDon chiTietHoaDon) {
		rest.postForEntity("chitiethoadon", chiTietHoaDon, ChiTietHoaDon.class);
	}

	@Override
	public ChiTietHoaDon findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ChiTietHoaDon> findByMaHoaDon(Long hoadonID) {
		// TODO Auto-generated method stub
		return rest.exchange("chitiethoadon/hoadon/"+hoadonID, HttpMethod.GET, null, new ParameterizedTypeReference<List<ChiTietHoaDon>>() {
		}).getBody();
	}

	@Override
	public void deleteCTHD(List<ChiTietHoaDon> chiTietHoaDons) {
		rest.delete("chitiethoadon");
		
	}

}
