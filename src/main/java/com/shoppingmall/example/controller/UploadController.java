package com.shoppingmall.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/uploadForm")
public class UploadController {
	
	@GetMapping("/")
	public String form() {
		return "/Upload.jsp";
	}
	
	// 업로드
//	@RequestMapping(value="/upload", method=RequestMethod.POST)
//	public String upload(@RequestParam("file") MultipartFile multipartFile) {
//		String path = "/Users/lcomputer/Documents/work/fileupload/src/main/resources/static/images/";
//		String thumbPath = path + "thumb/";
//		String filename = multipartFile.getOriginalFilename();
//		String ext = filename.substring(filename.lastIndexOf(".")+1);
//		
//		File file = new File(path + filename);
//		File thumbFile = new File(thumbPath + filename);
//		try {
//			// 원본파일 저장
//			InputStream input = multipartFile.getInputStream();
//			FileUtils.copyInputStreamToFile(input, file);
//			
//			// 썸네일 생성
//			BufferedImage imageBuf = ImageIO.read(file);
//			int fixWidth = 500;
//			double ratio = imageBuf.getWidth() / (double)fixWidth;
//			int thumbWidth = fixWidth;
//			int thumbHeight = (int)(imageBuf.getHeight() / ratio);
//			BufferedImage thumbImageBf = new BufferedImage(thumbWidth, thumbHeight, BufferedImage.TYPE_3BYTE_BGR);
//			Graphics2D g = thumbImageBf.createGraphics();
//			Image thumbImage = imageBuf.getScaledInstance(thumbWidth, thumbHeight, Image.SCALE_SMOOTH);
//			g.drawImage(thumbImage, 0, 0, thumbWidth, thumbHeight, null);
//			g.dispose();
//			ImageIO.write(thumbImageBf, ext, thumbFile);
//			
//		} catch (IOException e) {
//			FileUtils.deleteQuietly(file);
//			e.printStackTrace();
//		}
//		return "/printImage";
//	}

}