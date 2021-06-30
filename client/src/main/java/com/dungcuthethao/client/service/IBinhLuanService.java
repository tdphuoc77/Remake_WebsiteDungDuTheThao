package com.dungcuthethao.client.service;

import java.util.List;

import com.dungcuthethao.client.entity.BinhLuan;

public interface IBinhLuanService {
	void save(BinhLuan binhLuan);
	void deleteById(Long id);
	List<BinhLuan> findBySanPham(Long id);
}
