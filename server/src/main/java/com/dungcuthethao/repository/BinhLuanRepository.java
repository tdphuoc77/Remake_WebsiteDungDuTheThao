package com.dungcuthethao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dungcuthethao.entity.BinhLuan;

public interface BinhLuanRepository extends JpaRepository<BinhLuan, Long> {
	
	List<BinhLuan> findBySanphamId(Long id);
}
