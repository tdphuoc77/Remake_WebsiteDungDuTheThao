package com.dungcuthethao.client.service;

import java.util.List;

import com.dungcuthethao.client.entity.SanPham;

public interface ISanPhamService {
	List<SanPham> findByTen(String ten,int page,int limit);
	void save (SanPham sanPham);
	SanPham findById(Long id);
	void updateSanPham(SanPham sanPham);
	List<SanPham> findTop3SanPhamBySoLuotXem();
	List<SanPham> findDSSanPhamNoiBat();
	List<SanPham> findTop3SanPhamGiamGiaNhieuNhat();
	Long getTotalItem();
	void setTrangThaiSanPham(Long id, boolean tt);
	List<SanPham> getAll();
	List<SanPham> getAllAndPaging(int page,int limit);
	List<SanPham> getAllAndPagingAndSapXepTang(int page,int limit);
	List<SanPham> getAllAndPagingAndSapXepGiam(int page,int limit);
	List<SanPham> searchByTenAndSapXepTang(String ten,int page,int limit);
	List<SanPham> searchByTenAndSapXepGiam(String ten,int page,int limit);
	
	 List<SanPham> getByDanhMuc(Long idDM,int page,int limit);
	 List<SanPham> getByDanhMucTang(Long idDM,int page,int limit);
	 List<SanPham> getByDanhMucGiam(Long idDM,int page,int limit);
}
