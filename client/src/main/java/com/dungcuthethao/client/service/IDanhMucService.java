package com.dungcuthethao.client.service;

import java.util.List;


import com.dungcuthethao.client.entity.DanhMuc;

public interface IDanhMucService {
	List<DanhMuc> findAll();
	DanhMuc findByTen(String ten);
	List<DanhMuc> findAllDanhMucConById(Long id);
	List<DanhMuc> findAllDanhMucCha();
	void updateDanhMuc(DanhMuc danhMuc);
	void themDanhMuc(DanhMuc danhMuc);
	DanhMuc findOne(Long id);
	List<DanhMuc> findAllDanhMucCon();
}
