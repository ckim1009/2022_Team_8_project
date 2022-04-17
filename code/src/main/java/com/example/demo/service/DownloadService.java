package com.example.demo.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

@Service
public class DownloadService {

	public void downloadResult(String title, String body, HttpServletResponse response, File file)
	{
		BufferedOutputStream out = null;
	     InputStream in = null;
		 try {
			 //한글제목은 encoding해서 보내줘야함
			 String encordedFilename = URLEncoder.encode(title+".pdf","UTF-8").replace("+", "%20");
			 response.setHeader("Content-Disposition",   "attachment;filename=" + encordedFilename + ";filename*= UTF-8''" + encordedFilename);
			 response.setContentType("application/pdf");
			 //response.setHeader("Content-Disposition", "inline;filename="+tempArt.getSer_fileName());
	            //File file = new File("temp"+File.separator+title);//upDownloadDir로 바꿀 것
	            if(file.exists()) {
	                in = new FileInputStream(file);
	                out = new BufferedOutputStream(response.getOutputStream());
	                int len;
	                byte[] buf = new byte[1024];
	                while ((len = in.read(buf)) > 0) {
	                    out.write(buf, 0, len);
	                }
	            }
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	        	try {
	            if(out != null) { out.flush();}
	            if(out != null) { out.close();}
	            if(out != null) { in.close();}
	        	}catch(Exception e)
	        	{
	        		e.printStackTrace();
	        	}
	        }
		 
		 //임시파일 삭제
		 file.delete();
	}
}
