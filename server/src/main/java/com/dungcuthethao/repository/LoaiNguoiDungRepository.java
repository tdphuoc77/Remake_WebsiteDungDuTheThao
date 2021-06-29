package com.dungcuthethao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dungcuthethao.entity.LoaiNguoiDung;

public interface LoaiNguoiDungRepository extends JpaRepository<LoaiNguoiDung, Long> {

	LoaiNguoiDung findOneByTenLoai(String tenLoai);
}
