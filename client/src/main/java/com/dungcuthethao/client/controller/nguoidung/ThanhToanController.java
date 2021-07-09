package com.dungcuthethao.client.controller.nguoidung;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dungcuthethao.client.dto.GiohangSanphamDTO;
import com.dungcuthethao.client.dto.NguoiDungDTO;
import com.dungcuthethao.client.entity.ChiTietHoaDon;
import com.dungcuthethao.client.entity.HoaDon;
import com.dungcuthethao.client.entity.NguoiDung;
import com.dungcuthethao.client.entity.SanPham;
import com.dungcuthethao.client.service.impl.ChiTietHoaDonServiceImpl;
import com.dungcuthethao.client.service.impl.HoaDonServiceImpl;
import com.dungcuthethao.client.service.impl.NguoiDungServiceImpl;
import com.dungcuthethao.client.service.impl.SanPhamServiceImpl;
import com.dungcuthethao.client.util.SecurityUtils;

@Controller
public class ThanhToanController {
	@Autowired
	NguoiDungServiceImpl nguoiDungService;
	
	@Autowired
	HoaDonServiceImpl hoaDonService;
	
	@Autowired
	SanPhamServiceImpl sanPhamService;
	
	@Autowired
	
	private ChiTietHoaDonServiceImpl chiTietHoaDonServiceImpl;
		
	NguoiDung nguoidung = null;
	
	@RequestMapping(value = "/thanh-toan", method = RequestMethod.GET)
	public String checkout (Model model) {
		NguoiDungDTO nguoidungDTO = null;
		try {
			nguoidungDTO = SecurityUtils.getPrincipal();
		} catch (Exception e) {
			return "redirect:/dang-nhap";
		}

		nguoidung = nguoiDungService.findOneByTenDangNhap(nguoidungDTO.getUsername());
		model.addAttribute("nguoidung",nguoidung);
		
		return "user/thanhtoan";
	}
	@RequestMapping(value = "/thanh-toan", method = RequestMethod.POST)
	public String datHang (Model model, HttpSession session) {
//		them chi tiet hoa don
		
		@SuppressWarnings("unchecked")
		HashMap<Long, GiohangSanphamDTO> dsSanPhamGioHang  = (HashMap<Long, GiohangSanphamDTO>) session.getAttribute("gioHang");
		if(dsSanPhamGioHang==null) {
			return "redirect:/thanh-toan";
		}
		
		int soLuongSp = (int) session.getAttribute("tongSoLuongGioHang");
		double tongTienHD = (double) session.getAttribute("tongThanhTienGioHang");
//		kiem tra dia chi
		if(nguoidung.getDsDiaChi().size()==0) {
			return "redirect:/them-dia-chi/"+nguoidung.getId();
		}
		
//		lap hoa don
		HoaDon hd = new HoaDon(nguoidung,false,LocalDate.now(),LocalDate.now().plusDays(7),null,soLuongSp,tongTienHD);
		hoaDonService.saveHoaDon(hd);
		
//
		for (Map.Entry<Long, GiohangSanphamDTO> item : dsSanPhamGioHang.entrySet()) {
			ChiTietHoaDon ct = new ChiTietHoaDon();
			ct.setHoadon(hoaDonService.findById(hoaDonService.getAll().get(hoaDonService.getAll().size()-1).getId()));
			System.out.println(hoaDonService.findById(hoaDonService.getAll().get(hoaDonService.getAll().size()-1).getId()));
			ct.setSanpham(sanPhamService.findById(item.getValue().getSanPham().getId()));
			ct.setSoLuong(item.getValue().getSoLuong());
			chiTietHoaDonServiceImpl.saveCTHD(ct);
		}
		
//		cap nhat san pham (số lượng sp, số lượt mua) giảm
		dsSanPhamGioHang.forEach((k, v)-> {
			SanPham sp = sanPhamService.findById(v.getSanPham().getId());
			sp.setSoLuong(sp.getSoLuong() -v.getSoLuong());
			sp.setSoLuotMua(sp.getSoLuotMua()+1);
			sanPhamService.updateSanPham(sp);
		});
		
		
		session.removeAttribute("gioHang");
		session.removeAttribute("tongSoLuongGioHang");
		session.removeAttribute("tongThanhTienGioHang");
		return "user/thong_bao_dat_hang";
	}
}
