package com.dungcuthethao.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dungcuthethao.entity.GiaTriThuocTinhSanPham;
import com.dungcuthethao.repository.GiaTriThuocTinhSanPhamRepository;

@RestController
@RequestMapping("/giatrithuoctinhsanpham")
public class GiaTriThuocTinhSanPhamRestController {
	
	@Autowired
	private GiaTriThuocTinhSanPhamRepository giaTriThuocTinhSanPhamRepository;
	
//	void saveGTTTSP(Long sanPhamID,Long thuocTinhID,String giaTri);
//	void updateGTTTSP(SanPham sanPham);
//	GiaTriThuocTinhSanPham findByIdSanPhamAndIDThuocTinh(Long idSP,Long idTT);
	
	@GetMapping("/sanpham&&thuoctinh")
	public GiaTriThuocTinhSanPham getBySPAndTTSP(@RequestParam Long idSP,@RequestParam Long idTT) {
		
		return giaTriThuocTinhSanPhamRepository.findOneBySanphamIdAndThuoctinhsanphamId(idSP, idTT);
	}
	
	@PostMapping
	public GiaTriThuocTinhSanPham add(@RequestBody GiaTriThuocTinhSanPham giaTriThuocTinhSanPham) {
		giaTriThuocTinhSanPhamRepository.save(giaTriThuocTinhSanPham);
		return giaTriThuocTinhSanPham;
		
	}
	
	@PutMapping
	public GiaTriThuocTinhSanPham update(@RequestBody GiaTriThuocTinhSanPham giaTriThuocTinhSanPham) {
		giaTriThuocTinhSanPhamRepository.save(giaTriThuocTinhSanPham);
		return giaTriThuocTinhSanPham;
		
	}
	
}
