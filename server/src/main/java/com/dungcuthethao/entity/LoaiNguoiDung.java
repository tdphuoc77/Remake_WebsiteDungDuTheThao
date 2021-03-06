package com.dungcuthethao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LoaiNguoiDung")
public class LoaiNguoiDung {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(columnDefinition = "nvarchar(50)",name = "tenLoai")
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
