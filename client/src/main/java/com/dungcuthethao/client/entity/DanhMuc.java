package com.dungcuthethao.client.entity;

public class DanhMuc {
	private Long id;
	
	private String ten;
	
	private Long danhMucCha;
	public DanhMuc(Long id, String ten, Long danhMucCha) {
		super();
		this.id = id;
		this.ten = ten;
		this.danhMucCha = danhMucCha;
	}
	public DanhMuc(String ten, Long danhMucCha) {
		super();
		this.ten = ten;
		this.danhMucCha = danhMucCha;
	}
	public DanhMuc() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public Long getDanhMucCha() {
		return danhMucCha;
	}
	public void setDanhMucCha(Long danhMucCha) {
		this.danhMucCha = danhMucCha;
	}
	@Override
	public String toString() {
		return "DanhMuc [id=" + id + ", ten=" + ten + ", danhMucCha=" + danhMucCha + "]";
	}
	
}
