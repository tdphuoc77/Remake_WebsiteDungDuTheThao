package com.dungcuthethao.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@IdClass(ChiTietHoaDon_PK.class)
@Table(name = "ChiTietHoaDon")
public class ChiTietHoaDon {
	@Id
	@ManyToOne
	@JoinColumn(name = "sanphamID")
	
	private SanPham sanpham;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "hoadonID")
	private HoaDon hoadon;
	
	@Column(name = "soLuong")
	private int soLuong;
	public ChiTietHoaDon(SanPham sanpham, HoaDon hoadon, int soLuong) {
		super();
		this.sanpham = sanpham;
		this.hoadon = hoadon;
		this.soLuong = soLuong;
	}
	public ChiTietHoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SanPham getSanpham() {
		return sanpham;
	}
	public void setSanpham(SanPham sanpham) {
		this.sanpham = sanpham;
	}
	public HoaDon getHoadon() {
		return hoadon;
	}
	public void setHoadon(HoaDon hoadon) {
		this.hoadon = hoadon;
	}

	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	@Override
	public String toString() {
		return "ChiTietHoaDon [sanpham=" + sanpham + ", hoadon=" + hoadon  + ", soLuong="
				+ soLuong + "]";
	}
	
	
	
}
