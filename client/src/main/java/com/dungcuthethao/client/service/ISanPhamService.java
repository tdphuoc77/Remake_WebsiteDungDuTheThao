package com.dungcuthethao.client.service;

import java.util.List;

import com.dungcuthethao.client.entity.SanPham;

public interface ISanPhamService {
	List<SanPham> findByTen(String ten, int page, int limit);

	void save (SanPham sanPham);
	SanPham findById(Long id);
	void updateSanPham(SanPham sanPham);
	List<SanPham> findTop3SanPhamBySoLuotXem();
	List<SanPham> findDSSanPhamNoiBat();
	List<SanPham> findTop3SanPhamGiamGiaNhieuNhat();
	Long getTotalItem();
	void setTrangThaiSanPham(Long id, boolean tt);
	
}
