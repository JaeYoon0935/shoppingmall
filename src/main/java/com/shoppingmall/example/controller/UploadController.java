package com.shoppingmall.example.controller;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

//@Controller


//CrossOrigin을 사용하지 않으면 종종 8080(뷰)에 있는게 교류가 안될떄가 있어서 적어준다.
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/")
public class UploadController {
	
	//uploadForm이라는 url이 넘어오면, Upload.jsp를 띄워주는 테스트용 메서드
	@RequestMapping("/uploadForm")
	public String form() {
		return "/Upload.jsp";
	}
	
	 //업로드
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public String upload(@RequestParam("File") MultipartFile multipartFile) {
		
		String path = "/Users/l4/Documents/Project/shoppingmall/src/shoppingmall-vue/src/assets/";
		String thumbPath = path + "thumb/";
		String filename = multipartFile.getOriginalFilename();
		String ext = filename.substring(filename.lastIndexOf(".")+1);
		
		File file = new File(path + filename);
		File thumbFile = new File(thumbPath + filename);
		try {
			
			// 원본파일 저장
			InputStream input = multipartFile.getInputStream();
			FileUtils.copyInputStreamToFile(input, file);
						
			// 썸네일 생성
			BufferedImage imageBuf = ImageIO.read(file);
			int fixWidth = 500;
			double ratio = imageBuf.getWidth() / (double)fixWidth;
			int thumbWidth = fixWidth;
			int thumbHeight = (int)(imageBuf.getHeight() / ratio);
			BufferedImage thumbImageBf = new BufferedImage(thumbWidth, thumbHeight, BufferedImage.TYPE_3BYTE_BGR);
			Graphics2D g = thumbImageBf.createGraphics();
			Image thumbImage = imageBuf.getScaledInstance(thumbWidth, thumbHeight, Image.SCALE_SMOOTH);
			g.drawImage(thumbImage, 0, 0, thumbWidth, thumbHeight, null);
			g.dispose();
			ImageIO.write(thumbImageBf, ext, thumbFile);
					
			
		} catch (IOException e) {
			FileUtils.deleteQuietly(file);
			e.printStackTrace();
		}
		// /printImage는 .jsp페이지 이던데, 리턴으로 위에서 만든 파일을 해주면되는지?
		return "/printImage";
	}

}