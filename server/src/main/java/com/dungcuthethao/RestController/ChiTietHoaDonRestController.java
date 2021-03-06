package com.dungcuthethao.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dungcuthethao.entity.ChiTietHoaDon;
import com.dungcuthethao.repository.ChiTietHoaDonReposiotry;

@RestController
@RequestMapping("/chitiethoadon")
public class ChiTietHoaDonRestController {
	@Autowired
	private ChiTietHoaDonReposiotry chiTietHoaDonReposiotry;
	
//	void saveCTHD(ChiTietHoaDon chiTietHoaDon);
//	ChiTietHoaDon findById(Long id);
//	List<ChiTietHoaDon> findByMaHoaDon(Long hoadonID);
//	void deleteCTHD(List<ChiTietHoaDon> chiTietHoaDons);
	
	@GetMapping("/hoadon/{id}")
	public List<ChiTietHoaDon> getByHoaDon(@PathVariable Long id) {
		return chiTietHoaDonReposiotry.findByHoadonId(id);
	}
	@PostMapping
	public ChiTietHoaDon addChiTiet(@RequestBody ChiTietHoaDon chiTietHoaDon) {
		chiTietHoaDonReposiotry.save(chiTietHoaDon);
		return chiTietHoaDon;
	}
	
	@DeleteMapping
	public void delete(@RequestBody List<ChiTietHoaDon> chiTietHoaDons) {
		for (ChiTietHoaDon id: chiTietHoaDons) {
			chiTietHoaDonReposiotry.delete(id);
		}
		
	}
}
