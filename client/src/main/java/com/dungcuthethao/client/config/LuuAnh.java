package com.dungcuthethao.client.config;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class LuuAnh {
	public void luuAnh(MultipartFile anhSanPham,HttpSession session ) throws IOException {
		ServletContext context= session.getServletContext();
		String fileName= anhSanPham.getOriginalFilename();
		String path= context.getRealPath("/resources/images/user/sanpham");
		String filePath= path+ File.separator+fileName;
		byte[] bytes= anhSanPham.getBytes();
		BufferedOutputStream stream;
		try {
			stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
			stream.write(bytes);
			stream.flush();
			stream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
