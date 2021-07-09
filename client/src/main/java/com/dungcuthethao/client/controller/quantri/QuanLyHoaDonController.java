package com.dungcuthethao.client.controller.quantri;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dungcuthethao.client.config.SenMail;
import com.dungcuthethao.client.dto.AbstractDTO;
import com.dungcuthethao.client.entity.ChiTietHoaDon;
import com.dungcuthethao.client.entity.HoaDon;
import com.dungcuthethao.client.entity.NguoiDung;
import com.dungcuthethao.client.entity.SanPham;
import com.dungcuthethao.client.service.impl.ChiTietHoaDonServiceImpl;
import com.dungcuthethao.client.service.impl.HoaDonServiceImpl;
import com.dungcuthethao.client.service.impl.NguoiDungServiceImpl;
import com.dungcuthethao.client.service.impl.SanPhamServiceImpl;

@Controller
@RequestMapping("/quan-tri/quan-ly-hoa-don")
public class QuanLyHoaDonController {
	@Autowired
	HoaDonServiceImpl hoaDonService;
	
	@Autowired
	private NguoiDungServiceImpl nguoiDungService;
	
	@Autowired
	private SanPhamServiceImpl sanPhamService;
	
	@Autowired
	private ChiTietHoaDonServiceImpl chiTietHoaDonService;
	
	@Autowired
	private SenMail senMail;

	
	
	@GetMapping
	public String index(Model model, @RequestParam("page") int page,@RequestParam("limit") int limit) {
		
		AbstractDTO abstractDTO= new AbstractDTO();
		abstractDTO.setPage(page);
		abstractDTO.setLimit(limit);
		
		
		List<HoaDon> dsHD= hoaDonService.findListHoaDonDaGiao(page, limit);
		abstractDTO.setTotalItem((long) hoaDonService.findListHoaDonDaGiao(1,(int) hoaDonService.getAll().size()).size());
		System.out.println(hoaDonService.findListHoaDonDaGiao(1,(int) hoaDonService.getAll().size()).size()+"CHECLLLLDIJASDNJMIU");
		abstractDTO.setLimit(limit);
		
		abstractDTO.setTotalPage((int) Math.ceil(abstractDTO.getTotalItem()/abstractDTO.getLimit())+1);
		model.addAttribute("abstractDTO",abstractDTO);
		model.addAttribute("dsHD", dsHD);
		
		
		return"admin/quanlyhoadon";
	}
	
	
	@GetMapping("/xem-chi-tiet/{id}")
	public String chitietHoaDon(Model model,@PathVariable Long id) {
		List<SanPham> listSanPham= new ArrayList<SanPham>();
		System.out.println(id);
		HoaDon hoaDon=hoaDonService.findById(id);
		List<ChiTietHoaDon> listCT=chiTietHoaDonService.findByMaHoaDon(id);
		NguoiDung nguoiDung=nguoiDungService.findById(hoaDon.getNguoidung().getId());
		for (ChiTietHoaDon ct : listCT) {
			Long idSP=ct.getSanpham().getId();
			SanPham sanPham= sanPhamService.findById(idSP);
			listSanPham.add(sanPham);
		}
		model.addAttribute("hoaDon", hoaDon);
		model.addAttribute("listCT", listCT);
		model.addAttribute("nguoiDung", nguoiDung);
		model.addAttribute("listSP", listSanPham);
		return "admin/chitiethoadon";
	}
	
	
	@GetMapping("/xem-chi-tiet-chua-xac-nhan/{id}")
	public String chitietHoaDonChuaXacNhan(Model model,@PathVariable Long id) {
		List<SanPham> listSanPham= new ArrayList<SanPham>();
		HoaDon hoaDon=hoaDonService.findById(id);
		List<ChiTietHoaDon> listCT=chiTietHoaDonService.findByMaHoaDon(id);
		NguoiDung nguoiDung=nguoiDungService.findById(hoaDon.getNguoidung().getId());
		for (ChiTietHoaDon ct : listCT) {
			Long idSP=ct.getSanpham().getId();
			SanPham sanPham= sanPhamService.findById(idSP);
			listSanPham.add(sanPham);
		}
		model.addAttribute("hoaDon", hoaDon);
		model.addAttribute("listCT", listCT);
		model.addAttribute("nguoiDung", nguoiDung);
		model.addAttribute("listSP", listSanPham);
		return "admin/chitietdonhangchuaxacnhan";
	}
	
	@GetMapping("/don-hang-chua-xac-nhan")
	public String donHangChuaXacNhan(Model model, @RequestParam("page") int page,@RequestParam("limit") int limit) {
		
		AbstractDTO abstractDTO= new AbstractDTO();
		abstractDTO.setPage(page);
		abstractDTO.setLimit(limit);
		List<HoaDon> dsHD= hoaDonService.findListHoaChuaGiao(page, limit);
		abstractDTO.setTotalItem((long) hoaDonService.findListHoaChuaGiao(1,(int) hoaDonService.getAll().size()).size());
		abstractDTO.setLimit(limit);
		abstractDTO.setTotalPage((int) Math.ceil(abstractDTO.getTotalItem()/abstractDTO.getLimit())+1);
		model.addAttribute("abstractDTO",abstractDTO);
		model.addAttribute("dsHD", dsHD);
		return"admin/quanlydonhangchuaxacnhan";
	}
	@GetMapping("don-hang-chua-xac-nhan/xac-nhan/{id}")
	public String xacNhanDonHang(@PathVariable Long id) {
			HoaDon hoaDon= hoaDonService.findById(id);
			senMail.SenEmail(hoaDon.getNguoidung().getEmail(),
					"Xac nhan don hang","Don hang cua ban da duoc xac nhan và dang trong qua trinh van chuyen vui long xem cac thong tin duoi day:"+"\n"+"Tong tien:"+hoaDon.getTongTienHoaDon()+" VNĐ"+"\n"+"Ngay nhan du kien:"+hoaDon.getNgayNhanDuKien()+"\n"+"Xin chan thanh cam on su ung ho qua ban."+"\n"+"Moi chi tiet vui long lien he:0702074032");
			hoaDon.setTrangThai(true);
			hoaDonService.updateHoaDon(hoaDon);
		return"redirect:/quan-tri/quan-ly-hoa-don/don-hang-chua-xac-nhan?page=1&limit=6";
	}
	
	
	@GetMapping("don-hang-chua-xac-nhan/tu-choi/{id}")
	public String tuChoiDonHang(@PathVariable Long id) {

		try {
			List<ChiTietHoaDon>list= chiTietHoaDonService.findByMaHoaDon(id);
			list.forEach(cthd-> {
				int soLuongSanPhamDatHang = cthd.getSoLuong();
//				phục hồi lại số lượng sản phẩm
				SanPham p = cthd.getSanpham();
				p.setSoLuong(p.getSoLuong() + soLuongSanPhamDatHang);
				sanPhamService.save(p);
			});
			
//			xoa hoa don + xoa ds cthd
			HoaDon hoaDon= hoaDonService.findById(id);
			hoaDonService.deleteHoaDon(id);
			NguoiDung nguoiDung= nguoiDungService.findById(hoaDon.getNguoidung().getId());
//			gui mai cho khach hang
			senMail.SenEmail(nguoiDung.getEmail(),
					"Thong bao huy don dat hang",
					"Vi mot so li do don hang cua ban da bi huy. Chung toi chan thanh xin loi vi su bat tien nay."+"\n"+"hay truy cap vao website: http://localhost:8080/  cua chung toi de tiep tuc mua sap"+"\n moi chi tiet vui long lien he: 0702704302");
			
			
		} catch (Exception e) {
		}
		return"redirect:/quan-tri/quan-ly-hoa-don/don-hang-chua-xac-nhan?page=1&limit=6";
	}
}
