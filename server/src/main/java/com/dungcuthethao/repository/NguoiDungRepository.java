package com.dungcuthethao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dungcuthethao.entity.NguoiDung;

public interface NguoiDungRepository extends JpaRepository<NguoiDung, Long> {
	NguoiDung findOneByTenDangNhap(String tenDangNhap);
}
