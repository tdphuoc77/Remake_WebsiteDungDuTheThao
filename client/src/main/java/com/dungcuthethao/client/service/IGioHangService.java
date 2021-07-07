package com.dungcuthethao.client.service;

import java.util.HashMap;

import com.dungcuthethao.client.dto.GiohangSanphamDTO;

public interface IGioHangService {
	public HashMap<Long, GiohangSanphamDTO> themVaoGioHang(Long id, HashMap<Long, GiohangSanphamDTO> gioHang);
	public HashMap<Long, GiohangSanphamDTO> suaSanPhamGioHang(Long id, int soluongMoi, HashMap<Long, GiohangSanphamDTO> gioHang);
	public HashMap<Long, GiohangSanphamDTO> xoaSanPhamGioHang(Long id, HashMap<Long, GiohangSanphamDTO> gioHang);
	public int getTongSoLuongGioHang(HashMap<Long, GiohangSanphamDTO> gioHang);
	public double getTongThanhTienGioHang(HashMap<Long, GiohangSanphamDTO> gioHang);
	
}
