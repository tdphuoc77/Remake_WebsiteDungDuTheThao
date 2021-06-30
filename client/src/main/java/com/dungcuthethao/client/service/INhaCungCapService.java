package com.dungcuthethao.client.service;

import java.util.List;

import com.dungcuthethao.client.entity.NhaCungCap;

public interface INhaCungCapService {
	NhaCungCap saveNCC(NhaCungCap nhaCungCap);
	void updateNCC(NhaCungCap nhaCungCap);
	NhaCungCap findByTenNhaCungCap(String ten);
	NhaCungCap findNCCByID(Long id);
	List<NhaCungCap> findAll();
	void deleteNCC(Long id);
	List<NhaCungCap> getAllAndPaging(int page, int limit);
}
