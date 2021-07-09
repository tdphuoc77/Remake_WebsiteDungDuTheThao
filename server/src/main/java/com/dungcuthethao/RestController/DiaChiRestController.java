package com.dungcuthethao.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dungcuthethao.entity.DiaChi;
import com.dungcuthethao.repository.DiaChiRepository;

@RestController
@RequestMapping("/diachi")
public class DiaChiRestController {
	
	@Autowired
	private DiaChiRepository diaChiRestpository;
	@GetMapping("/nguoidung/{idND}")
	public List<DiaChi> getByNguoiDung(@PathVariable Long idND) {
		return diaChiRestpository.findByNguoidungId(idND);
	}
	
	@PostMapping
	public DiaChi addDiaChi(@RequestBody DiaChi diaChi) {
		 diaChiRestpository.save(diaChi);
		return diaChi;
	}
	
	@PutMapping
	public DiaChi updateDiaChi(@RequestBody DiaChi diaChi) {
		return diaChiRestpository.save(diaChi);
	}
	
	@DeleteMapping("/{id}")
	public void deleteDiaChi(@PathVariable Integer id) {
		diaChiRestpository.deleteById(id);
	}
}
