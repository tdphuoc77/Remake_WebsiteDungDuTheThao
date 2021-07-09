package com.dungcuthethao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dungcuthethao.entity.DiaChi;

public interface DiaChiRepository extends JpaRepository<DiaChi, Integer> {
	List<DiaChi> findByNguoidungId(Long idND);
}
