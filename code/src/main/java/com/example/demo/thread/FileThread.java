package com.example.demo.thread;

import com.example.demo.service.FileService;

public class FileThread extends Thread{
	String title;
	String body;
	
	public FileThread(String title, String body) {
		System.out.println("쓰레드 시작 : "+title);
		this.title = title;
		this.body = body;
	}
	
	public FileThread ()
	{
		
	}
	
	@Override
	public void run()
	{
		FileService.makeTextFile(title, body);
	}
}
