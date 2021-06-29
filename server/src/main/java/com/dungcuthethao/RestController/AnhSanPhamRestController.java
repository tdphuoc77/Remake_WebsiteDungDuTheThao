package com.dungcuthethao.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dungcuthethao.entity.AnhSanPham;
import com.dungcuthethao.repository.AnhSanPhamRepository;

@RestController
@RequestMapping("/anhsanpham")
public class AnhSanPhamRestController {
	
	@Autowired
	private AnhSanPhamRepository anhSanPhamRepository;
	
	@GetMapping
	public List<AnhSanPham> getBySP(@PathVariable Long id) {
		return anhSanPhamRepository.findBySanphamId(id);
	}
}
