package com.dungcuthethao.client.controller.quantri;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dungcuthethao.client.config.LuuAnh;
import com.dungcuthethao.client.dto.AbstractDTO;
import com.dungcuthethao.client.dto.ThemSanPhamDTO;
import com.dungcuthethao.client.entity.DanhMuc;
import com.dungcuthethao.client.entity.GiaTriThuocTinhSanPham;
import com.dungcuthethao.client.entity.NhaCungCap;
import com.dungcuthethao.client.entity.SanPham;
import com.dungcuthethao.client.entity.ThuocTinhSanPham;
import com.dungcuthethao.client.service.impl.DanhMucServiceImpl;
import com.dungcuthethao.client.service.impl.GiaTriThuocTinhSanPhamServiceImpl;
import com.dungcuthethao.client.service.impl.NhaCungCapServiceImpl;
import com.dungcuthethao.client.service.impl.SanPhamServiceImpl;
import com.dungcuthethao.client.service.impl.ThuocTinhSanPhamServiceImpl;
import com.dungcuthethao.client.validate.SanPhamSuaValidation;
import com.dungcuthethao.client.validate.SanPhamThemValidation;

@Controller
@RequestMapping("/quan-tri/quan-ly-san-pham")
public class QuanLiSanPhamController {
	

	private SanPham sanP=null;
	ThemSanPhamDTO spSua = null;
	@Autowired
	private ThuocTinhSanPhamServiceImpl thuocTinhSanPhamService;
	
	@Autowired
	private GiaTriThuocTinhSanPhamServiceImpl giaTriThuocTinhSanPhamService;
	
	@Autowired
	private DanhMucServiceImpl danhMucService;
	@Autowired
	private SanPhamServiceImpl sanPhamService;
	
	@Autowired
	private NhaCungCapServiceImpl nhaCungCapService;
	
	
	@Autowired
	private LuuAnh luuAnh;
	
	@Autowired
	private SanPhamThemValidation sanPhamThemValidation;
	
	@Autowired
	private SanPhamSuaValidation sanPhamSuaValidation;
	
	@GetMapping
	public String index(Model model, @RequestParam("page") int page,@RequestParam("limit") int limit) {
		List<DanhMuc>list= danhMucService.findAllDanhMucCon();
		
		AbstractDTO abstractDTO= new AbstractDTO();
		abstractDTO.setPage(page);
		abstractDTO.setLimit(limit);
		
		List<SanPham> dsSanPham= sanPhamService.getAllAndPaging(page, limit);

		abstractDTO.setTotalItem((long) sanPhamService.getAll().size());
		abstractDTO.setLimit(limit);
		
		abstractDTO.setTotalPage((int) Math.ceil(abstractDTO.getTotalItem()/abstractDTO.getLimit()+1));
		model.addAttribute("list",list);
		model.addAttribute("abstractDTO",abstractDTO);
		model.addAttribute("dsSanPham", dsSanPham);
		
		return"admin/quanlisanpham";
	}

	@GetMapping("/them-san-pham")
	public String themSanPham(Model model) {
		ThemSanPhamDTO sanPham= new ThemSanPhamDTO();
		model.addAttribute("sanPham", sanPham);	
		List<DanhMuc> listDanhMuc= danhMucService.findAll();
		model.addAttribute("listDanhMuc", listDanhMuc);
		List<NhaCungCap> listNhaCungCap= nhaCungCapService.findAll(); 
		model.addAttribute("listNhaCungCap", listNhaCungCap);
		
		List<ThuocTinhSanPham> listThuocTinh= thuocTinhSanPhamService.findAll();
		model.addAttribute("listThuocTinh", listThuocTinh);
		
		return "admin/themsanpham";
	}
	
	
	@PostMapping("/them-san-pham")
	public String luuSanPham(@ModelAttribute("sanPham") ThemSanPhamDTO sanPham,HttpSession session,BindingResult bindingResult, Model model) throws IOException{
		sanPhamThemValidation.validate(sanPham, bindingResult);
		if(bindingResult.hasErrors()) {
			List<DanhMuc> listDanhMuc= danhMucService.findAll();
			model.addAttribute("listDanhMuc", listDanhMuc);
			List<NhaCungCap> listNhaCungCap= nhaCungCapService.findAll(); 
			model.addAttribute("listNhaCungCap", listNhaCungCap);
			
			List<ThuocTinhSanPham> listThuocTinh= thuocTinhSanPhamService.findAll();
			model.addAttribute("listThuocTinh", listThuocTinh);
			model.addAttribute("mesErr","Thông tin đăng ký không hợp lệ");
			return"admin/themsanpham";
		}
		SanPham sp= new SanPham();
		sp.setAnhDaiDien(sanPham.getAnhDaiDien().getOriginalFilename());
		sp.setTen(sanPham.getTen());
		sp.setDanhmuc(danhMucService.findByTen(sanPham.getTenDanhMuc()));
		sp.setNhacungcap(nhaCungCapService.findByTenNhaCungCap(sanPham.getTenNhaCungCap()));
		sp.setMoTa(sanPham.getMoTa());
		sp.setThuongHieu(sanPham.getThuongHieu());
		sp.setNoiDung(sanPham.getNoiDung());
		sp.setGia(Double.parseDouble(sanPham.getGia()));
		sp.setPhanTramGiamGia(0);
		sp.setSoLuong(Integer.parseInt(sanPham.getSoLuong()));
		sp.setSoLuotMua(0);
		sp.setSoLuotXem(0);
		sp.setThoiGianBaoHanh(Integer.parseInt(sanPham.getThoiGianBaoHanh()));
		sp.setTrangThai(true);
		sp.setNgayTao(LocalDate.now());
		sanPhamService.save(sp);
		luuAnh.luuAnh(sanPham.getAnhDaiDien(), session);
		
		if(!sanPham.getGiaTriThuocTinh().equals("")) {
			ThuocTinhSanPham thuocTinhSanPham= thuocTinhSanPhamService.findOneByTenThuoctinh(sanPham.getTenThuocTinh());
			giaTriThuocTinhSanPhamService.saveGTTTSP(new GiaTriThuocTinhSanPham(sanPhamService.getAll().get(sanPhamService.getAll().size()-1)
					, thuocTinhSanPham, sanPham.getGiaTriThuocTinh()));
		}
		return "redirect:/quan-tri/quan-ly-san-pham?page=1&limit=12";
	}
	
	@GetMapping("/xoa-san-pham/{id}")
	public String tamNgungSanPham(@PathVariable Long id) {
		try {
			SanPham sanPham=sanPhamService.findById(id);
			if(sanPham.isTrangThai()==true) {
				sanPham.setTrangThai(false);
				sanPhamService.updateSanPham(sanPham);
			}
			else {
				sanPham.setTrangThai(true);
				sanPhamService.updateSanPham(sanPham);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/quan-tri/quan-ly-san-pham?page=1&limit=12";
	}
	
	@GetMapping("/sua-san-pham/{id}")
	public String suaSanPham(@PathVariable Long id,Model model) {

		SanPham sanPham= sanPhamService.findById(id);
		sanP= sanPham;
		ThemSanPhamDTO themSanPhamDTO = new ThemSanPhamDTO(sanPham.getTen(), sanPham.getMoTa(), sanPham.getThuongHieu(), sanPham.getNoiDung(),String.valueOf( sanPham.getGia()),String.valueOf(sanPham.getPhanTramGiamGia()),String.valueOf( sanPham.getThoiGianBaoHanh()));
		model.addAttribute("themSanPhamDTO", themSanPhamDTO);
		return "admin/suasanpham";
	}
	@PostMapping("/sua-san-pham/luu-thong-tin")
	public String suaSanPham(@ModelAttribute("themSanPhamDTO") ThemSanPhamDTO themSanPhamDTO,BindingResult  bindingResult,HttpSession session,Model model) throws IOException {
		
		sanPhamSuaValidation.validate(themSanPhamDTO, bindingResult);
		if(bindingResult.hasErrors()) {
			model.addAttribute("themSanPhamDTO", themSanPhamDTO);
			return"admin/suasanpham";
		}
		
		
		luuAnh.luuAnh(themSanPhamDTO.getAnhDaiDien(), session);
		
		sanP.setTen(themSanPhamDTO.getTen());
		sanP.setAnhDaiDien(themSanPhamDTO.getAnhDaiDien().getOriginalFilename());
		sanP.setGia(Double.parseDouble(themSanPhamDTO.getGia()));
		sanP.setMoTa(themSanPhamDTO.getMoTa());
		sanP.setNoiDung(themSanPhamDTO.getNoiDung());
		sanP.setThoiGianBaoHanh(Integer.parseInt(themSanPhamDTO.getThoiGianBaoHanh()));
		sanP.setThuongHieu(themSanPhamDTO.getThuongHieu());
		sanP.setPhanTramGiamGia(Double.parseDouble(themSanPhamDTO.getPhanTramGiamGia()));
		sanPhamService.updateSanPham(sanP);
		return "redirect:/quan-tri/quan-ly-san-pham?page=1&limit=12";
		
		
	}
	@GetMapping("/them-thuoc-tinh-san-pham")
	public String themThuocTinhSanPham(Model model) {
		return "admin/themthuoctinhsanpham";
	}
	@PostMapping("/them-thuoc-tinh-san-pham")
	public String themThuocTinhSanPhamSubmit(@RequestParam String tenThuocTinh , Model model) {
		ThuocTinhSanPham thuocTinhSanPham = new ThuocTinhSanPham(tenThuocTinh);
		thuocTinhSanPhamService.saveTTSP(thuocTinhSanPham);
		return "redirect:/quan-tri/quan-ly-san-pham/them-san-pham";
	}
	
	@GetMapping("/loc-san-pham/{id}")
	public String locSanPham(@PathVariable Long id, Model model, @RequestParam("page") int page,@RequestParam("limit") int limit) {
		List<DanhMuc>list= danhMucService.findAllDanhMucCon();
		
		AbstractDTO abstractDTO= new AbstractDTO();
		abstractDTO.setPage(page);
		abstractDTO.setLimit(limit);
		
		
		
		List<SanPham> dsSanPham= sanPhamService.getByDanhMuc(id, page, limit);
		abstractDTO.setTotalItem((long) dsSanPham.size());
		abstractDTO.setLimit(limit);
		abstractDTO.setTotalPage((int) Math.ceil(abstractDTO.getTotalItem()/abstractDTO.getLimit() +1));
		model.addAttribute("list",list);
		model.addAttribute("abstractDTO",abstractDTO);
		model.addAttribute("dsSanPham", dsSanPham);
		
		// bien check kiem tra form submit vao dau
		model.addAttribute("idDanhMuc",id);
		return"admin/quanlisanpham";
	}
	
	
}
