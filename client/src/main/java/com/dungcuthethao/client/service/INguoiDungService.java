package com.dungcuthethao.client.service;

import java.util.List;

import com.dungcuthethao.client.entity.NguoiDung;

public interface INguoiDungService {
	void saveNguoiDung(NguoiDung nguoiDung);
	void UpdateNguoiDung(NguoiDung nguoiDung);
	NguoiDung findById(Long id);
	List<NguoiDung> findByTenAndHo(String ten,String ho);
	
	
	NguoiDung findOneByTenDangNhap(String tenDangNhap);
	Long getTotalItem();
	void setTrangThaiNguoiDung(Long id, boolean tt);
	 List<NguoiDung> findAll();
		
}
