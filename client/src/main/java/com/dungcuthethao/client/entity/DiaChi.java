package com.dungcuthethao.client.entity;

public class DiaChi{
	private int id;
	
	private NguoiDung nguoidung;
	private String soNha;
	private String quan;

	
	private String tinhTP;

	
	public DiaChi() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public NguoiDung getNguoidung() {
		return nguoidung;
	}

	public void setNguoidung(NguoiDung nguoidung) {
		this.nguoidung = nguoidung;
	}

	public String getQuan() {
		return quan;
	}

	public void setQuan(String quan) {
		this.quan = quan;
	}

	public String getSoNha() {
		return soNha;
	}

	public void setSoNha(String soNha) {
		this.soNha = soNha;
	}

	public String getTinhTP() {
		return tinhTP;
	}

	public void setTinhTP(String tinhTP) {
		this.tinhTP = tinhTP;
	}

	@Override
	public String toString() {
		return ""+soNha + ", " + quan + ", " + tinhTP ;
	}

	public DiaChi(NguoiDung nguoidung, String soNha, String quan, String tinhTP) {
		super();
		this.nguoidung = nguoidung;
		this.soNha = soNha;
		this.quan = quan;
		this.tinhTP = tinhTP;
	}
	
	
}
