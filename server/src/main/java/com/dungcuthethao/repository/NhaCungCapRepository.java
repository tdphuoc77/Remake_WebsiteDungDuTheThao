package com.dungcuthethao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dungcuthethao.entity.NhaCungCap;

public interface NhaCungCapRepository extends JpaRepository<NhaCungCap, Long> {
	
	NhaCungCap findOneByTenNhaCungCap(String ten);

}
