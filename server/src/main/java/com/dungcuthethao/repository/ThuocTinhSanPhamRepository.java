package com.dungcuthethao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dungcuthethao.entity.ThuocTinhSanPham;

public interface ThuocTinhSanPhamRepository extends JpaRepository<ThuocTinhSanPham, Long> {
	ThuocTinhSanPham findOneByTenThuoctinh(String ten);
}
