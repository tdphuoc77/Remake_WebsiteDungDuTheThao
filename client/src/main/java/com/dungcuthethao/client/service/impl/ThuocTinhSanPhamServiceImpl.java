package com.dungcuthethao.client.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dungcuthethao.client.entity.ThuocTinhSanPham;
import com.dungcuthethao.client.service.IThuocTinhSanPhamService;

@Service
public class ThuocTinhSanPhamServiceImpl implements IThuocTinhSanPhamService {

	@Autowired
	private RestTemplate rest;

	@Override
	public void saveTTSP(ThuocTinhSanPham thuocTinhSanPham) {
	rest.postForEntity("thuoctinhsanpham", thuocTinhSanPham, ThuocTinhSanPham.class);
		
	}

	@Override
	public void updateTTSP(ThuocTinhSanPham thuocTinhSanPham) {
	rest.put("thuoctinhsanpham", thuocTinhSanPham);
	}

	@Override
	public ThuocTinhSanPham findById(Long id) {
		// TODO Auto-generated method stub
		return rest.getForObject("thuoctinhsanpham/"+id, ThuocTinhSanPham.class);
	}

	@Override
	public ThuocTinhSanPham findOneByTenThuoctinh(String ten) {
		// TODO Auto-generated method stub
		return rest.getForObject("thuoctinhsanpham/ten?ten="+ten, ThuocTinhSanPham.class);
	}

	@Override
	public List<ThuocTinhSanPham> findAll() {
		// TODO Auto-generated method stub
		return rest.exchange("thuoctinhsanpham", HttpMethod.GET, null, new ParameterizedTypeReference<List<ThuocTinhSanPham>>() {
		}).getBody();
	}
	
}
