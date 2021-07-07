package com.dungcuthethao.client.controller.nguoidung;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dungcuthethao.client.config.LuuAnh;
import com.dungcuthethao.client.dto.AbstractDTO;
import com.dungcuthethao.client.dto.NguoiDungDTO;
import com.dungcuthethao.client.entity.BinhLuan;
import com.dungcuthethao.client.entity.DanhMuc;
import com.dungcuthethao.client.entity.NguoiDung;
import com.dungcuthethao.client.entity.SanPham;
import com.dungcuthethao.client.service.impl.BinhLuanServiceImpl;
import com.dungcuthethao.client.service.impl.DanhMucServiceImpl;
import com.dungcuthethao.client.service.impl.NguoiDungServiceImpl;
import com.dungcuthethao.client.service.impl.SanPhamServiceImpl;
import com.dungcuthethao.client.util.SecurityUtils;

@Controller
public class SanPhamController {
	@Autowired
	private SanPhamServiceImpl sanPhamService;
	
	@Autowired 
    private	DanhMucServiceImpl danhService;
	
	@Autowired
	NguoiDungServiceImpl nguoiDungService;
	@Autowired
	private BinhLuanServiceImpl binhLuanService;
	@Autowired
	private LuuAnh luuAnh;
	
	@GetMapping("/danh-sach-san-pham")
	public String index (Model model, @RequestParam("page") int page,@RequestParam("limit") int limit) {
		AbstractDTO abstractDTO= new AbstractDTO();
		abstractDTO.setPage(page);
		abstractDTO.setLimit(limit);
		
		
		
		List<SanPham> dsSanPham= sanPhamService.getAllAndPaging(page, limit);

		abstractDTO.setTotalItem((long) sanPhamService.getAll().size());
		abstractDTO.setLimit(limit);
		
		abstractDTO.setTotalPage((int) Math.ceil(abstractDTO.getTotalItem()/abstractDTO.getLimit())+1);
		model.addAttribute("abstractDTO",abstractDTO);
		model.addAttribute("dsSanPham", dsSanPham);
		
		List<DanhMuc> listDanhMuc = danhService.findAllDanhMucCon();
		model.addAttribute("listDanhMuc", listDanhMuc);
		
		return "user/danhsachsanpham";
	}
	
	@GetMapping("/danh-sach-san-pham/danh-muc/{id}")
	public String getDsSanPhamByDanhMuc(@PathVariable Long id, Model model, @RequestParam("page") int page,@RequestParam("limit") int limit) {
		
		AbstractDTO abstractDTO= new AbstractDTO();
		abstractDTO.setPage(page);
		abstractDTO.setLimit(limit);
		
		
		List<SanPham> dsSanPham= sanPhamService.getByDanhMuc(id, page, limit);
		abstractDTO.setTotalItem((long) dsSanPham.size());
		abstractDTO.setLimit(limit);
		
		abstractDTO.setTotalPage((int) Math.ceil(abstractDTO.getTotalItem()/abstractDTO.getLimit())+1);
		model.addAttribute("abstractDTO",abstractDTO);
		model.addAttribute("dsSanPham", dsSanPham);
		model.addAttribute("danhmucID", id);
		
		List<DanhMuc> listDanhMuc = danhService.findAllDanhMucCon();
		model.addAttribute("listDanhMuc", listDanhMuc);
		return "user/danhsachsanpham_theodanhmuc";
	}
//	
	@GetMapping("/danh-sach-san-pham/danh-muc/{id}/sap-xep/{value}")
	public String getDsSanPhamByDanhMucVaSapXep(@PathVariable String value, @PathVariable Long id, Model model, @RequestParam("page") int page,@RequestParam("limit") int limit) {
		
		AbstractDTO abstractDTO= new AbstractDTO();
		abstractDTO.setPage(page);
		abstractDTO.setLimit(limit);
		
		List<SanPham> dsSanPham= null;
		if(value.equals("asc")) {
			dsSanPham= sanPhamService.getByDanhMucTang(id, page, limit);
		}
		else {
			dsSanPham= sanPhamService.getByDanhMucGiam(id, page, limit);
		}
		
		abstractDTO.setTotalItem((long) dsSanPham.size());
		abstractDTO.setLimit(limit);
		
		abstractDTO.setTotalPage((int) Math.ceil(abstractDTO.getTotalItem()/abstractDTO.getLimit())+1);
		model.addAttribute("abstractDTO",abstractDTO);
		model.addAttribute("dsSanPham", dsSanPham);
		model.addAttribute("danhmucID", id);
		
//		giaTriSapXep thuộc asc or desc
		model.addAttribute("giaTriSapXep",value);
		
		List<DanhMuc> listDanhMuc = danhService.findAllDanhMucCon();
		model.addAttribute("listDanhMuc", listDanhMuc);
		return "user/danhsachsanpham_theodanhmuc";
	}
//	
	@GetMapping("/danh-sach-san-pham/sap-xep/{value}")
	public String sapXep(@PathVariable String value, Model model,  @RequestParam("page") int page,@RequestParam("limit") int limit) {
		AbstractDTO abstractDTO= new AbstractDTO();
		abstractDTO.setPage(page);
		abstractDTO.setLimit(limit);
		
		List<SanPham> dsSanPham=null;
		if(value.equals("asc")) {
			 dsSanPham = sanPhamService.getAllAndPagingAndSapXepTang(page, limit);
		}
		else {
			dsSanPham = sanPhamService.getAllAndPagingAndSapXepGiam(page, limit);
		}
		
		System.out.println("Checkkk"+ dsSanPham.size());
		abstractDTO.setLimit(limit);
		
		abstractDTO.setTotalPage((int) Math.ceil(abstractDTO.getTotalItem()/abstractDTO.getLimit())+1);
		model.addAttribute("abstractDTO",abstractDTO);
		model.addAttribute("dsSanPham", dsSanPham);
		
//		giaTriSapXep thuộc asc or desc
		model.addAttribute("giaTriSapXep",value);
		
		List<DanhMuc> listDanhMuc = danhService.findAllDanhMucCon();
		model.addAttribute("listDanhMuc", listDanhMuc);
		return "user/danhsachsanpham";
	}
//	
	@GetMapping("/danh-sach-san-pham/tim-kiem/{keywork}")
	public String timKiem (Model model, @PathVariable String keywork,  @RequestParam("page") int page, @RequestParam("limit") int limit ) {

		AbstractDTO abstractDTO= new AbstractDTO();
		abstractDTO.setPage(page);
		abstractDTO.setLimit(limit);
		
		
		List<SanPham> dsSanPham= sanPhamService.findByTen(keywork, page, limit);
		abstractDTO.setTotalItem(dsSanPham.size()*1L);
		abstractDTO.setLimit(limit);
		
		abstractDTO.setTotalPage((int) Math.ceil(abstractDTO.getTotalItem()/abstractDTO.getLimit())+1);
		model.addAttribute("abstractDTO",abstractDTO);
		model.addAttribute("dsSanPham", dsSanPham);
		model.addAttribute("keywork",keywork);
		List<DanhMuc> listDanhMuc = danhService.findAllDanhMucCon();
		model.addAttribute("listDanhMuc", listDanhMuc);
		return "user/ketqua_timkiem";
	}
	@GetMapping(value = "/danh-sach-san-pham/tim-kiem/{keywork}/sap-xep/{value}")
	public String timKiemVaSapXep (Model model, @PathVariable String keywork, @PathVariable String value, @RequestParam("page") int page,@RequestParam("limit") int limit ) {
		AbstractDTO abstractDTO= new AbstractDTO();
		abstractDTO.setPage(page);
		abstractDTO.setLimit(limit);
		
		List<SanPham> dsSanPham= null;
		if(value.equals("asc")) {
			dsSanPham= sanPhamService.searchByTenAndSapXepGiam(keywork, page, limit);
		}
		else {
			dsSanPham= sanPhamService.searchByTenAndSapXepTang(keywork, page, limit);
		}
		
		
		
		abstractDTO.setTotalItem((long) dsSanPham.size());
		abstractDTO.setLimit(limit);
		
		abstractDTO.setTotalPage((int) Math.ceil(abstractDTO.getTotalItem()/abstractDTO.getLimit())+1);
		model.addAttribute("abstractDTO",abstractDTO);
		model.addAttribute("dsSanPham", dsSanPham);
		model.addAttribute("keywork",keywork);
//		giaTriSapXep thuộc asc or desc
		model.addAttribute("giaTriSapXep",value);
		
		List<DanhMuc> listDanhMuc = danhService.findAllDanhMucCon();
		model.addAttribute("listDanhMuc", listDanhMuc);
		return "user/ketqua_timkiem";
	}
	
	@RequestMapping("/chi-tiet-san-pham/{id}")
	public String index(@PathVariable Long id, Model model) {
		SanPham sp = sanPhamService.findById(id);

		List<BinhLuan> listBL = binhLuanService.findBySanPham(sp.getId());

		model.addAttribute("listBL", listBL);

		model.addAttribute("sp", sp);

		return "user/chitietsanpham";
	}

//	chi-tiet-san-pham/luu-binh-luan

	@RequestMapping("/chi-tiet-san-pham/luu-binh-luan/{id}")
	public String luuBinhLuan(@PathVariable Long id,HttpSession session,@RequestParam("binhLuan") String binhLuan,@RequestParam MultipartFile anhSanPham) throws IOException {
		NguoiDungDTO nguoidungDTO = null;
		try {
			nguoidungDTO = SecurityUtils.getPrincipal();
		} catch (Exception e) {
			return "redirect:/dang-nhap";
		}
		NguoiDung nguoidung = nguoiDungService.findOneByTenDangNhap(nguoidungDTO.getUsername());
		if (binhLuan != null) {
			luuAnh.luuAnh(anhSanPham, session);
//			binhLuanService.themBinhLuan(anhSanPham.getOriginalFilename(), binhLuan, LocalDate.now(), nguoidung.getId(),id);
			binhLuanService.save(new BinhLuan(nguoidung, sanPhamService.findById(id), binhLuan, anhSanPham.getOriginalFilename(), LocalDate.now()));;
		}

		return "redirect:/chi-tiet-san-pham/"+id;
	}
}
