package com.dungcuthethao.client.service;

import com.dungcuthethao.client.entity.GiaTriThuocTinhSanPham;
import com.dungcuthethao.client.entity.SanPham;

public interface IGiaTriThuocTinhSanPhamService {
	void saveGTTTSP(GiaTriThuocTinhSanPham giaTriThuocTinhSanPham);
	void updateGTTTSP(SanPham sanPham);
	GiaTriThuocTinhSanPham findByIdSanPhamAndIDThuocTinh(Long idSP,Long idTT);
}
