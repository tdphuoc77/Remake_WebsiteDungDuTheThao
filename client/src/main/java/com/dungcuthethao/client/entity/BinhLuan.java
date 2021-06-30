package com.dungcuthethao.client.entity;
import java.time.LocalDate;

public class BinhLuan {
	
	private Long id;
	
	private NguoiDung nguoidung;
	
	private SanPham sanpham;
	
	private String binhLuan;

	private String anhSanPham;
	private LocalDate ngayBinhLuan;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public NguoiDung getNguoidung() {
		return nguoidung;
	}
	public void setNguoidung(NguoiDung nguoidung) {
		this.nguoidung = nguoidung;
	}
	public SanPham getSanpham() {
		return sanpham;
	}
	public void setSanpham(SanPham sanpham) {
		this.sanpham = sanpham;
	}
	public String getBinhLuan() {
		return binhLuan;
	}
	public void setBinhLuan(String binhLuan) {
		this.binhLuan = binhLuan;
	}
	public String getAnhSanPham() {
		return anhSanPham;
	}
	public void setAnhSanPham(String anhSanPham) {
		this.anhSanPham = anhSanPham;
	}
	public LocalDate getNgayBinhLuan() {
		return ngayBinhLuan;
	}
	public void setNgayBinhLuan(LocalDate ngayBinhLuan) {
		this.ngayBinhLuan = ngayBinhLuan;
	}
	/**
	 * @param nguoidung
	 * @param sanpham
	 * @param binhLuan
	 * @param anhSanPham
	 * @param ngayBinhLuan
	 */
	public BinhLuan(NguoiDung nguoidung, SanPham sanpham, String binhLuan, String anhSanPham, LocalDate ngayBinhLuan) {
		super();
		this.nguoidung = nguoidung;
		this.sanpham = sanpham;
		this.binhLuan = binhLuan;
		this.anhSanPham = anhSanPham;
		this.ngayBinhLuan = ngayBinhLuan;
	}
	public BinhLuan(Long id, NguoiDung nguoidung, SanPham sanpham, String binhLuan, String anhSanPham,
			LocalDate ngayBinhLuan) {
		super();
		this.id = id;
		this.nguoidung = nguoidung;
		this.sanpham = sanpham;
		this.binhLuan = binhLuan;
		this.anhSanPham = anhSanPham;
		this.ngayBinhLuan = ngayBinhLuan;
	}
	@Override
	public String toString() {
		return "BinhLuan [id=" + id + ", nguoidung=" + nguoidung + ", sanpham=" + sanpham + ", binhLuan=" + binhLuan
				+ ", anhSanPham=" + anhSanPham + ", ngayBinhLuan=" + ngayBinhLuan + "]";
	}
	public BinhLuan() {
		super();
	}
	
	
	

}
