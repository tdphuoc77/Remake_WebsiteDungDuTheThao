package com.dungcuthethao.client.controller.nguoidung;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dungcuthethao.client.dto.AbstractDTO;
import com.dungcuthethao.client.entity.DanhMuc;
import com.dungcuthethao.client.entity.SanPham;
import com.dungcuthethao.client.service.impl.DanhMucServiceImpl;
import com.dungcuthethao.client.service.impl.SanPhamServiceImpl;

@Controller
@RequestMapping(value = "/danh-sach-san-pham")
public class SanPhamController {
	@Autowired
	private SanPhamServiceImpl sanPhamService;
	
	@Autowired 
    private	DanhMucServiceImpl danhService;
	
	@GetMapping
	public String index (Model model, @RequestParam("page") int page,@RequestParam("limit") int limit) {
		System.out.println(page);
		System.out.println(limit);
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
	
	@GetMapping("danh-muc/{id}")
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
	@GetMapping("danh-muc/{id}/sap-xep/{value}")
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
	@GetMapping("sap-xep/{value}")
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
		
		abstractDTO.setTotalItem((long) dsSanPham.size());		
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
	@GetMapping("/tim-kiem/{keywork}")
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
	@GetMapping(value = "/tim-kiem/{keywork}/sap-xep/{value}")
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
}
