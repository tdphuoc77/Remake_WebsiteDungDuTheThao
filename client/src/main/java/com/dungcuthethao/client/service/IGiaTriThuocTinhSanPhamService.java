package com.dungcuthethao.client.service;

import com.dungcuthethao.client.entity.GiaTriThuocTinhSanPham;
import com.dungcuthethao.client.entity.SanPham;

public interface IGiaTriThuocTinhSanPhamService {
	void saveGTTTSP(GiaTriThuocTinhSanPham giaTriThuocTinhSanPham);
	void updateGTTTSP(GiaTriThuocTinhSanPham gtttsanPham);
	GiaTriThuocTinhSanPham findByIdSanPhamAndIDThuocTinh(Long idSP,Long idTT);
}
