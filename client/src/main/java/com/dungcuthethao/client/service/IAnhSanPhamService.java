package com.dungcuthethao.client.service;

import com.dungcuthethao.client.entity.AnhSanPham;

public interface IAnhSanPhamService {
	void saveAnhSP(AnhSanPham anhSanPham);
	void updateAnhSP(AnhSanPham anhSanPham);
	void deleteAnhSP(Long id);
}
