package com.dungcuthethao.client.controller.nguoidung;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dungcuthethao.client.dto.NguoiDungDTO;
import com.dungcuthethao.client.entity.ChiTietHoaDon;
import com.dungcuthethao.client.entity.HoaDon;
import com.dungcuthethao.client.entity.NguoiDung;
import com.dungcuthethao.client.entity.SanPham;
import com.dungcuthethao.client.service.impl.ChiTietHoaDonServiceImpl;
import com.dungcuthethao.client.service.impl.HoaDonServiceImpl;
import com.dungcuthethao.client.service.impl.NguoiDungServiceImpl;
import com.dungcuthethao.client.util.SecurityUtils;

@Controller
@RequestMapping("/don-hang")
public class DonHangController {
	@Autowired
	NguoiDungServiceImpl nguoiDungService;
	@Autowired
	private HoaDonServiceImpl hoaDonService;

	@Autowired
	private ChiTietHoaDonServiceImpl chitietService;
	
	@GetMapping
	public String formXacNhanDonHang(Model model) {
		
		NguoiDungDTO nguoidungDTO = null;
		try {
			nguoidungDTO = SecurityUtils.getPrincipal();
		} catch (Exception e) {
			return "redirect:/dang-nhap";
		}
		
		NguoiDung nguoidung = nguoiDungService.findOneByTenDangNhap(nguoidungDTO.getUsername());
		
		model.addAttribute("nguoidung", nguoidung);
		
		List<HoaDon> listHD=hoaDonService.getByNguoiDungChuaNhan(nguoidung.getId());
		model.addAttribute("listHD", listHD);
		model.addAttribute("soLuongDonHang", listHD.size());
				
		return "user/xacnhandonhang";
	}
	
	@GetMapping("/xac-nhan/{id}")
	public String xacNhan(@PathVariable Long id) {
		try {
			hoaDonService.getByNguoiDungChuaNhan(id);
		} catch (Exception e) {
		}
		try {
			HoaDon hoaDon=hoaDonService.findById(id);
			hoaDon.setNgayNhan(LocalDate.now());
			hoaDon.setNguoiDungXacNhan(true);
			hoaDonService.updateHoaDon(hoaDon);
		} catch (Exception e) {
		}
		return "redirect:/don-hang";
	}
	
//	/don-hang-da-nhan/${nguoidung.id}
	
	@GetMapping("/don-hang-da-nhan/{id}")
	public String formDonHangDaNhan(Model model,@PathVariable Long id) {
		@SuppressWarnings("unused")
		NguoiDungDTO nguoidungDTO = null;
		try {
			nguoidungDTO = SecurityUtils.getPrincipal();
		} catch (Exception e) {
			return "redirect:/dang-nhap";
		}
		List<HoaDon> listHD=hoaDonService.getByNguoiDungNhan(id);
		model.addAttribute("listHD", listHD);
				
		return "user/donhangdanhan";
	}
	
//	don-hang/don-hang-da-nhan/xem-chi-tiet/3
	
	@GetMapping("/don-hang-da-nhan/xem-chi-tiet/{id}")
	public String xemChiTietDonHangDaNhan(Model model,@PathVariable Long id) {
		HoaDon hoaDon =hoaDonService.findById(id);
		List<ChiTietHoaDon> listCTHD= chitietService.findByMaHoaDon(id);
		NguoiDung nguoiDung=hoaDon.getNguoidung();
		List<SanPham> listSP=new ArrayList<SanPham>();
		for (ChiTietHoaDon chiTietHoaDon : listCTHD) {
			listSP.add(chiTietHoaDon.getSanpham());
		}
		model.addAttribute("hoaDon", hoaDon);
		model.addAttribute("listSP", listSP);
		model.addAttribute("nguoiDung", nguoiDung);
		return "user/xemchitietdonhangdanhan";
	}
	
}
