package com.dungcuthethao.client.controller.quantri;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dungcuthethao.client.dto.AbstractDTO;
import com.dungcuthethao.client.dto.NguoiDungDTO;
import com.dungcuthethao.client.entity.NguoiDung;
import com.dungcuthethao.client.service.impl.NguoiDungServiceImpl;
import com.dungcuthethao.client.util.SecurityUtils;

@Controller
@RequestMapping("/quan-tri/quan-ly-nguoi-dung")
public class QuanLyNguoiDungController {
	@Autowired
	private NguoiDungServiceImpl nguoiDungService;
	
	@GetMapping
	public String index(Model model, @RequestParam("page") int page,@RequestParam("limit") int limit) {
		
		NguoiDungDTO nguoiDungDTO=null;
		try {
			nguoiDungDTO=SecurityUtils.getPrincipal();
		} catch (Exception e) {
		}
		NguoiDung nguoiDung= nguoiDungService.findOneByTenDangNhap(nguoiDungDTO.getUsername());
		
		AbstractDTO abstractDTO= new AbstractDTO();
		abstractDTO.setPage(page);
		abstractDTO.setLimit(limit);
		List<NguoiDung> listNguoiDung= nguoiDungService.findAllAndPaging(page, limit);
		
		if(listNguoiDung.contains(nguoiDung)) {
			listNguoiDung.remove(nguoiDung);
		}
		abstractDTO.setTotalItem((long) nguoiDungService.findAll().size());
		abstractDTO.setLimit(limit);
		
		abstractDTO.setTotalPage((int) Math.ceil(abstractDTO.getTotalItem()/abstractDTO.getLimit())+1);
		model.addAttribute("abstractDTO",abstractDTO);
		model.addAttribute("listNguoiDung", listNguoiDung);
		
		
		return"admin/quanlynguoidung";
	}
	
	@RequestMapping("/sua-trang-thai/{id}")
	public String suaTrangThaiNguoiDung(@PathVariable Long id) {
		try {
			NguoiDung nguoiDung=nguoiDungService.findById(id);
			if(nguoiDung.isTrangThai()==true) {
				nguoiDung.setTrangThai(false);
				nguoiDungService.UpdateNguoiDung(nguoiDung);
			}else {
				nguoiDung.setTrangThai(true);
				nguoiDungService.UpdateNguoiDung(nguoiDung);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return "redirect:/quan-tri/quan-ly-nguoi-dung?page=1&limit=12";
	}

	
}
