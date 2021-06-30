package com.dungcuthethao.client.service;

import java.util.List;

import com.dungcuthethao.client.entity.DiaChi;

public interface IDiaChiService {
	void saveDiaChi(DiaChi diaChi);
	List<DiaChi> findByNguoiDungID(Long id);
	
}
