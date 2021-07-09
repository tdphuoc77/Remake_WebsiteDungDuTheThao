package com.dungcuthethao.client.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dungcuthethao.client.dto.NguoiDungDTO;
import com.dungcuthethao.client.entity.LoaiNguoiDung;
import com.dungcuthethao.client.entity.NguoiDung;

@Service("customUserService")
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private NguoiDungServiceImpl nguoiDungService;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		NguoiDung nguoiDung= nguoiDungService.findOneByTenDangNhap(username);
		
		if(nguoiDung ==null) {
			throw new UsernameNotFoundException("Không tim thấy người dùng");
		}
		// put thong tin nguoi dung vao security  vÃ  duy tri dang nhap
		
		LoaiNguoiDung loaiNguoiDung= nguoiDung.getLoainguoidung();

		
		
		List<GrantedAuthority> authorties =new ArrayList<GrantedAuthority>();
		authorties.add(new SimpleGrantedAuthority(loaiNguoiDung.getTenLoai()));
		
		
		NguoiDungDTO nguoiDungDTO= new NguoiDungDTO(nguoiDung.getTenDangNhap(), nguoiDung.getMatKhau(), true, true, true, true, authorties);
		nguoiDungDTO.setFullName(nguoiDung.getHo()+" "+nguoiDung.getTen());
		return nguoiDungDTO;
	}
}
