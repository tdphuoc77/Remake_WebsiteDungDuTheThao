package com.dungcuthethao.client.service;

import com.dungcuthethao.client.entity.LoaiNguoiDung;

public interface ILoaiNguoiDungService {
	LoaiNguoiDung findByID(Long id);
	LoaiNguoiDung findByTenLoaiNguoiDung(String tenLoai);
}
