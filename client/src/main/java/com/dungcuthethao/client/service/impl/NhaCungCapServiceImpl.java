package com.dungcuthethao.client.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dungcuthethao.client.entity.NhaCungCap;
import com.dungcuthethao.client.service.INhaCungCapService;

@Service
public class NhaCungCapServiceImpl implements INhaCungCapService{
	
	@Autowired
	private RestTemplate rest;

	@Override
	public NhaCungCap saveNCC(NhaCungCap nhaCungCap) {
		// TODO Auto-generated method stub
		rest.postForEntity("nhacungcap", nhaCungCap, NhaCungCap.class);
		return nhaCungCap;
	}

	@Override
	public void updateNCC(NhaCungCap nhaCungCap) {
		rest.put("nhacungcap", nhaCungCap);
		
	}

	@Override
	public NhaCungCap findByTenNhaCungCap(String ten) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NhaCungCap findNCCByID(Long id) {
		// TODO Auto-generated method stub
		return rest.getForObject("nhacungcap/"+id, NhaCungCap.class);
	}

	@Override
	public List<NhaCungCap> findAll() {
		// TODO Auto-generated method stub
		return rest.exchange("nhacungcap", HttpMethod.GET, null, new ParameterizedTypeReference<List<NhaCungCap>>() {
		}).getBody();
	}

	@Override
	public void deleteNCC(Long id) {
		rest.delete("nhacungcap/"+id);
		
	}

	@Override
	public List<NhaCungCap> getAllAndPaging(int page, int limit) {
		// TODO Auto-generated method stub
		return rest.exchange("nhacungcap/phantrang?page="+page+"&&limit="+limit, HttpMethod.GET, null, new ParameterizedTypeReference<List<NhaCungCap>>() {
		}).getBody();
	}

}
