package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import com.example.demo.service.DownloadService;
import com.example.demo.service.FileService;
import com.example.demo.service.PDFService;

@Controller
public class MainController {
	
	@Autowired
	FileService fileService;
	@Autowired
	PDFService pdfService;
	@Autowired
	DownloadService downloadService;

	@RequestMapping(path = {"","/"}, method = RequestMethod.GET)
	public String main()
	{
		return "main";
	}
	
	@ResponseBody
	@PostMapping(value = { "/pdf_upload" }, consumes = { "multipart/form-data" })
	public JSONObject upload(@RequestParam(value="file", required=true) MultipartFile file) throws IOException
	{
		String filename = file.getOriginalFilename();
		
		//사용자가 업로드한 파일 서버에 저장
		File result_file = fileService.MultipartFile_to_File(file);
		//pdf파일의 텍스트 추출
		String text = pdfService.getText_spire(result_file);
		//정규표현식으로 필요없는 띄어쓰기, 줄바꿈 제거
		text = text.replace(System.lineSeparator(), "");
		text = text.replaceAll("-\\s[0-9]+\\s-", "");
		text = text.replaceAll("\\[[0-9]+\\]", "");
		text = text.replace("                 ", "");
		text = text.replace(", ", ",");
		text = text.replace(". ", ".");
		text = text.replace(",", ", ");
		text = text.replace(".", ". ");
		
		//json형태로 리턴
		HashMap<String,String> mymap = new HashMap<String,String>();
		mymap.put("title", filename.substring(0, filename.indexOf(".pdf"))+"의 요약본");
		mymap.put("body", text);
		JSONObject data = new JSONObject(mymap);
		

		return data;
	}
	
	//텍스트 입력
	@ResponseBody
	@PostMapping(value = { "/text_upload" })
	public JSONObject text_upload(String textbody) throws IOException
	{
		System.out.println(textbody);
		
		String title = fileService.get_format_time();
		
		
		//json형태로 리턴
		HashMap<String,String> mymap = new HashMap<String,String>();
		mymap.put("title", "텍스트 요약본");
		mymap.put("body", textbody);
		JSONObject data = new JSONObject(mymap);
		

		return data;
	}
	
	@PostMapping("/download")
	public void download(String title, String body, HttpServletResponse response){
		//System.out.println("body : "+body);
		File file = fileService.String_to_File(title, body);
		downloadService.downloadResult(title, body, response, file);
	}

}
