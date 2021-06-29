package com.dungcuthethao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dungcuthethao.entity.AnhSanPham;

public interface AnhSanPhamRepository extends JpaRepository<AnhSanPham, Long> {
	List<AnhSanPham> findBySanphamId(Long idSP);
}
