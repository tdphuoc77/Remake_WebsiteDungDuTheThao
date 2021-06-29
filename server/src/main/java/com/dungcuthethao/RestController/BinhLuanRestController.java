package com.dungcuthethao.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dungcuthethao.entity.BinhLuan;
import com.dungcuthethao.repository.BinhLuanRepository;

@RestController
@RequestMapping("/binhluan")
public class BinhLuanRestController {
	
	@Autowired
	private BinhLuanRepository binhLuanRepository;
	
	@GetMapping
	public List<BinhLuan> getBySP(@PathVariable Long id) {
		return binhLuanRepository.findBySanphamId(id);
	}
}
