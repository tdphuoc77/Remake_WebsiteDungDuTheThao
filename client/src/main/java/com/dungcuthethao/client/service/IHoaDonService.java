package com.dungcuthethao.client.service;

import java.util.List;

import com.dungcuthethao.client.entity.HoaDon;

public interface IHoaDonService {
	void saveHoaDon(HoaDon hoaDon);
	HoaDon findById(Long id);
//	void themDSChiTietHoaDon(Long idHD,HashMap<Long, GiohangSanphamDTO> gioHang);
	Long getTotalItem();
	void deleteHoaDon(Long id);
//	void setTrangThaiHoaDon(Long id, boolean tt);
	
	void updateHoaDon(HoaDon hoaDon);
	
//	void setNgayNhan( LocalDate ngayNhan ,Long id);
	List<HoaDon> findListHoaDonDaGiao();
	List<HoaDon> findListHoaChuaGiao();
	List<HoaDon> getByNguoiDungNhan(Long idND);
	List<HoaDon> getByNguoiDungChuaNhan(Long idND);
	
	
	
}
