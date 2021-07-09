package com.dungcuthethao.client.controller.nguoidung;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dungcuthethao.client.entity.SanPham;
import com.dungcuthethao.client.service.impl.SanPhamServiceImpl;

@Controller
public class TrangChuController {
	@Autowired
	private SanPhamServiceImpl phamServiceImpl;
	
	@GetMapping(value = {"/","/trang-chu"})
	public String index(Model model) {
		ArrayList<SanPham> dsSanPhamNoiBat = (ArrayList<SanPham>) phamServiceImpl.findDSSanPhamNoiBat();
		model.addAttribute("dsSanPhamNoiBat", dsSanPhamNoiBat);
		
		ArrayList<SanPham> dsSanPhamLuotXemCaoNhat = (ArrayList<SanPham>) phamServiceImpl.findTop3SanPhamBySoLuotXem();
		model.addAttribute("dsSanPhamLuotXemCaoNhat", dsSanPhamLuotXemCaoNhat);

		ArrayList<SanPham> dsSanPhamGiamGiaCaoNhat = (ArrayList<SanPham>) phamServiceImpl.findTop3SanPhamGiamGiaNhieuNhat();
		model.addAttribute("dsSanPhamGiamGiaCaoNhat", dsSanPhamGiamGiaCaoNhat);
		
		model.addAttribute("spGiamGiaTrongNgay",dsSanPhamNoiBat.get(0));

		return "user/trangchu";
	}
	
	@GetMapping("thong-tin-cua-hang")
	public String thongTinCuaHang() {
		return "user/thongtincuahang";
	}
	
	@GetMapping("gioi-thieu")
	public String gioiThieu() {
		return "user/gioithieu";
	}
	
	@GetMapping("bao-hanh")
	public String baoHanh() {
		return "user/chinhsachbaohanh";
	}

}
