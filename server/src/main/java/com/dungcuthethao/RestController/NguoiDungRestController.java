package com.dungcuthethao.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dungcuthethao.entity.NguoiDung;
import com.dungcuthethao.repository.NguoiDungRepository;

@RestController
@RequestMapping("/nguoidung")
public class NguoiDungRestController {
	@Autowired
	private NguoiDungRepository nguoiDungRepository;
	
//	List<NguoiDung> findAllAndPaging(Pageable pageable);
//	void saveNguoiDung(NguoiDung nguoiDung);
//	void UpdateNguoiDung(NguoiDung nguoiDung);
//	NguoiDung findById(Long id);
//	List<NguoiDung> findByTenAndHo(String ten,String ho);
//	
//	void doiMatKhau(TaiKhoanDTO taiKhoanDTO);
//	
//	NguoiDung findOneByTenDangNhap(String tenDangNhap);
//	Long getTotalItem();
//	void setTrangThaiNguoiDung(Long id, boolean tt);
//	 List<NguoiDung> findAll();
	
	@GetMapping("/phantrang")
	public List<NguoiDung> getAllAndPhanTrang(@RequestParam int page,@RequestParam int limit) {
		Pageable pageable=PageRequest.of(page-1, limit);
		return nguoiDungRepository.findAll(pageable).getContent();
	}
	@GetMapping
	public List<NguoiDung> getAll(){
		return nguoiDungRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public NguoiDung getById(@PathVariable Long id) {
		return nguoiDungRepository.findById(id).get();
	}
	
	@PostMapping
	public NguoiDung addND(@RequestBody NguoiDung nguoiDung) {
		nguoiDungRepository.save(nguoiDung);
		return nguoiDung;
	}
	
	@PutMapping
	public NguoiDung updateND(@RequestBody NguoiDung nguoiDung) {
		nguoiDungRepository.save(nguoiDung);
		return nguoiDung;
	}
	
	@PutMapping("/doimatkhau/{id}")
	public NguoiDung updateMatKhau(@RequestParam String matKhau,@PathVariable Long id) {
		NguoiDung  nguoiDung= nguoiDungRepository.findById(id).get();
		nguoiDung.setMatKhau(matKhau);
		nguoiDungRepository.save(nguoiDung);
		 return nguoiDung;
	}
	
	@GetMapping("/tendangnhap")
	public NguoiDung getByTenDangNhap(@RequestParam String ten) {
		return nguoiDungRepository.findOneByTenDangNhap(ten);
		
	}
	@PutMapping("/doitrangthai/{id}")
	public NguoiDung updateTrangThai(@RequestParam boolean tt,@PathVariable Long id) {
		NguoiDung  nguoiDung= nguoiDungRepository.findById(id).get();
		nguoiDung.setTrangThai(tt);
		nguoiDungRepository.save(nguoiDung);
		 return nguoiDung;
	}
	
	@GetMapping("/tongso")
	public int getTotal() {
		return (int) nguoiDungRepository.count();
	}
	
}
