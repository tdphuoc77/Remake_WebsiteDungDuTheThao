package com.dungcuthethao.client.service;

import java.util.List;

import com.dungcuthethao.client.entity.AnhSanPham;

public interface IAnhSanPhamService {
	void saveAnhSP(AnhSanPham anhSanPham);
	void updateAnhSP(AnhSanPham anhSanPham);
	void deleteAnhSP(Long id);
	
	List<AnhSanPham> getBysp(Long id);
	
}
