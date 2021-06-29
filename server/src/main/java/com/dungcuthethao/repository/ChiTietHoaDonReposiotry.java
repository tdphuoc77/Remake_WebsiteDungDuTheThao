package com.dungcuthethao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dungcuthethao.entity.ChiTietHoaDon;

public interface ChiTietHoaDonReposiotry  extends JpaRepository<ChiTietHoaDon, Long>{
	List<ChiTietHoaDon> findByHoadonId(Long id);
}
