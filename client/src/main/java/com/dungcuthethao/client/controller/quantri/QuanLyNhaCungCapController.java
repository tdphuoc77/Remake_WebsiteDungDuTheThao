package com.dungcuthethao.client.controller.quantri;

import java.util.List;

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

import com.dungcuthethao.client.dto.AbstractDTO;
import com.dungcuthethao.client.entity.NhaCungCap;
import com.dungcuthethao.client.service.impl.NhaCungCapServiceImpl;
import com.dungcuthethao.client.validate.NhaCungCapValidation;

@Controller
@RequestMapping("/quan-tri/nha-cung-cap")
public class QuanLyNhaCungCapController {
	private NhaCungCap ncc=null;
	@Autowired
	private NhaCungCapServiceImpl nhaCungCapService;
	@Autowired
	private NhaCungCapValidation nhaCungCapValidation;
	
	@GetMapping
	public String index(Model model, @RequestParam("page") int page,@RequestParam("limit") int limit) {
		AbstractDTO abstractDTO= new AbstractDTO();
		abstractDTO.setPage(page);
		abstractDTO.setLimit(limit);
		List<NhaCungCap> listNCC= nhaCungCapService.getAllAndPaging(page, limit);
		System.out.println("CHECKKKKK"+listNCC);
		abstractDTO.setTotalItem((long) nhaCungCapService.findAll().size());
		abstractDTO.setLimit(limit);
		abstractDTO.setTotalPage((int) Math.ceil(abstractDTO.getTotalItem()/abstractDTO.getLimit())+1);
		model.addAttribute("listNCC",listNCC);
		model.addAttribute("abstractDTO",abstractDTO);
		return"admin/quanlynhacungcap";
	}
	@GetMapping("/xoa-nha-cung-cap/{id}")
	public String xoaNhaCungCap(@PathVariable Long id) {
		nhaCungCapService.deleteNCC(id);;
		return"redirect:/quan-tri/nha-cung-cap?page=1&limit=12";
	}
	
	@GetMapping("/sua-nha-cung-cap/{id}")
	public String formSuaNhaCungCap(Model model,@PathVariable Long id) {
		NhaCungCap nhaCungCap=nhaCungCapService.findNCCByID(id);
		ncc=nhaCungCap;
		model.addAttribute("nhaCungCap", nhaCungCap);
		return "admin/suanhacungcap";
	}
	@PostMapping("/sua-nha-cung-cap/luu-thong-tin")
	public String luuThongTin(@ModelAttribute NhaCungCap nhaCungCap,Model model,BindingResult bindingResult) {
		nhaCungCapValidation.validate(nhaCungCap, bindingResult);
		if(bindingResult.hasErrors()) {
			model.addAttribute("nhaCungCap", nhaCungCap);
			return "quantri/suanhacungcap";
		}
		ncc.setDiachi(nhaCungCap.getDiachi());
		ncc.setEmail(nhaCungCap.getEmail());
		ncc.setSoDienThoai(nhaCungCap.getSoDienThoai());
		ncc.setTenNhaCungCap(nhaCungCap.getTenNhaCungCap());
		nhaCungCapService.updateNCC(ncc);
		return "redirect:/quan-tri/nha-cung-cap?page=1&limit=12";
	}
	@GetMapping("/them-nha-cung-cap")
	public String formThemNhaCungCap(Model model) {
		
		model.addAttribute("nhaCungCap", new NhaCungCap());
		return "admin/themnhacungcap";
	}
	
	@PostMapping("/them-nha-cung-cap")
	public String formThemNhaCungCap(@ModelAttribute NhaCungCap nhaCungCap,BindingResult bindingResult,Model model) {
		nhaCungCapValidation.validate(nhaCungCap, bindingResult);
		if(bindingResult.hasErrors()) {
			model.addAttribute("nhaCungCap", nhaCungCap);
			return "admin/themnhacungcap";
		}
		nhaCungCapService.saveNCC(nhaCungCap);
		return "redirect:/quan-tri/nha-cung-cap?page=1&limit=12";
	}
}
