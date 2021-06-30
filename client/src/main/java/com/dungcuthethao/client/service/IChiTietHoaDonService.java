package com.dungcuthethao.client.service;

import java.util.List;

import com.dungcuthethao.client.entity.ChiTietHoaDon;

public interface IChiTietHoaDonService {
	void saveCTHD(ChiTietHoaDon chiTietHoaDon);
	ChiTietHoaDon findById(Long id);
	List<ChiTietHoaDon> findByMaHoaDon(Long hoadonID);
	void deleteCTHD(List<ChiTietHoaDon> chiTietHoaDons);
}
