package com.example.demo.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

	String folder_name = "temp";
	
	public FileService()
	{
		File file = new File(folder_name);
		if(!file.exists())
		{
			file.mkdir();
		}
	}
	
	public String get_format_time()
	{
		LocalDateTime now = LocalDateTime.now();
		String formatedNow = now.format(DateTimeFormatter.ofPattern("MMddHHmmss"));
		return formatedNow;
	}
	
	public File MultipartFile_to_File (MultipartFile file)
	{
		
		File f = new File(folder_name+File.separator+file.getOriginalFilename());
	
		FileOutputStream fos;
		try {
			f.createNewFile();
			fos = new FileOutputStream(f);
			fos.write(file.getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//f.delete();
		
		return f;
	}

	public File String_to_File(String title, String body)
	{
		File f = new File(folder_name+File.separator+title+".txt");
		
		FileOutputStream fos;
		try {
			f.createNewFile();
			fos = new FileOutputStream(f);
			fos.write(body.getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//f.renameTo(new File("temp"+File.separator+title+".hwp"));
		return f;
	}
	
	public static void makeTextFile(String title, String body)
	{
		
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("python" + File.separator+title+".txt"),"UTF8"));
			writer.write(body);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if(writer!=null)
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

		
		
	}
	
}
