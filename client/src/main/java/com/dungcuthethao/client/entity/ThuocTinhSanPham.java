package com.dungcuthethao.client.entity;

import java.util.ArrayList;
import java.util.List;
public class ThuocTinhSanPham {
	private Long id;
	
	private String tenThuoctinh;
	
	private List<GiaTriThuocTinhSanPham> dsGiaTriThuocTinh = new ArrayList<GiaTriThuocTinhSanPham>();

	public ThuocTinhSanPham(Long id, String tenThuoctinh) {
		super();
		this.id = id;
		this.tenThuoctinh = tenThuoctinh;
	}

	public ThuocTinhSanPham(String tenThuoctinh) {
		super();
		this.tenThuoctinh = tenThuoctinh;
	}
	

	public ThuocTinhSanPham() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTenThuoctinh() {
		return tenThuoctinh;
	}

	public void setTenThuoctinh(String tenThuoctinh) {
		this.tenThuoctinh = tenThuoctinh;
	}

	public List<GiaTriThuocTinhSanPham> getDsGiaTriThuocTinh() {
		return dsGiaTriThuocTinh;
	}

	public void setDsGiaTriThuocTinh(List<GiaTriThuocTinhSanPham> dsGiaTriThuocTinh) {
		this.dsGiaTriThuocTinh = dsGiaTriThuocTinh;
	}

	@Override
	public String toString() {
		return "ThuocTinhSanPham [id=" + id + ", tenThuoctinh=" + tenThuoctinh + "]";
	}

	
	
	
	
}
