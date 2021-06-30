package com.dungcuthethao.client.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dungcuthethao.client.entity.GiaTriThuocTinhSanPham;
import com.dungcuthethao.client.service.IGiaTriThuocTinhSanPhamService;

@Service
public class GiaTriThuocTinhSanPhamServiceImpl implements IGiaTriThuocTinhSanPhamService{

	@Autowired
	private RestTemplate rest;
	@Override
	public void saveGTTTSP(GiaTriThuocTinhSanPham giaTriThuocTinhSanPham) {
		rest.postForEntity("giatrithuoctinhsanpham", giaTriThuocTinhSanPham, GiaTriThuocTinhSanPham.class);
		
	}

	@Override
	public void updateGTTTSP(GiaTriThuocTinhSanPham gtttsanPham) {
		rest.put("giatrithuoctinhsanpham", GiaTriThuocTinhSanPham.class);
		
	}

	@Override
	public GiaTriThuocTinhSanPham findByIdSanPhamAndIDThuocTinh(Long idSP, Long idTT) {
		// TODO Auto-generated method stub
		return rest.getForObject("giatrithuoctinhsanpham/sanpham&&thuoctinh?idSP="+idSP+"&&idTT="+idTT, GiaTriThuocTinhSanPham.class);
	}

}
