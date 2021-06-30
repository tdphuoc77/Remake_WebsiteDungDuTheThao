package com.dungcuthethao.client.entity;

public class LoaiNguoiDung {
	private Long id;
	
	private String tenLoai;
	public LoaiNguoiDung(Long id, String tenLoai) {
		super();
		this.id = id;
		this.tenLoai = tenLoai;
	}

	public LoaiNguoiDung(String tenLoai) {
		super();
		this.tenLoai = tenLoai;
	}
	public LoaiNguoiDung() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTenLoai() {
		return tenLoai;
	}
	public void setTenLoai(String tenLoai) {
		this.tenLoai = tenLoai;
	}
	@Override
	public String toString() {
		return "LoaiNguoiDung [id=" + id + ", tenLoai=" + tenLoai + "]";
	}
	
	
}
