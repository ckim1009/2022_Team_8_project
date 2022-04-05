package com.example.demo.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Service;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.PumpStreamHandler;

import java.io.ByteArrayOutputStream;

@Service
public class PythonService {

	
	public String execApachePy(File text)
	{
		int[] exitvalues = {0,1};
		String[] command = new String[4];
		command[0] = "python";
		command[1] = "python/kobart_demo.py";
		command[2] = text.getAbsolutePath();


		String result = "요약결과 : ";
		try {
			CommandLine commandLine = CommandLine.parse(command[0]);
	        for (int i = 1, n = command.length; i < n; i++) {
	            commandLine.addArgument(command[i]);
	        }

	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        PumpStreamHandler pumpStreamHandler = new PumpStreamHandler(outputStream);
	        DefaultExecutor executor = new DefaultExecutor();
	        executor.setStreamHandler(pumpStreamHandler);
	        executor.setExitValues(exitvalues);//파이썬 프로그램 에러 리턴 시 exit() 종료 번호를 입력
	        ExecuteWatchdog watchdog = new ExecuteWatchdog(100000);
	        executor.setWatchdog(watchdog);
	        
	        executor.execute(commandLine);

	        //System.out.println("output: " + outputStream.toString());
	        result += outputStream.toString("euc-kr");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	//파이썬 실행 후 종료하기까지 대기를 못함
	public String execPython(File text)
	{
		int i=0;
		String line = "";
		String result = "요약결과 : ";
		String execPath = "python python/test.py \""+text.getAbsolutePath()+"\"";
		try {
			Process process = Runtime.getRuntime().exec(execPath);
			process.waitFor();
			System.out.println("파이썬 종료");
			
			BufferedReader input =new BufferedReader(new InputStreamReader(process.getInputStream(), "euc-kr"));
	        while ((line = input.readLine()) !=null) {
	            System.out.println(i++);
	            result+=line;
	        }
	        
	        input.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(result);
		
		return result;
	}
	
	//파일을 읽지 못하는 버전
	public String executePython(String text)
	{
		String result="";
		
		 // TODO Auto-generated method stub
	      try {
	      String command = "C:\\Users\\tz1009\\AppData\\Local\\Programs\\Python\\Python310\\python.exe";  // 명령어
	      String arg1 = "python/test.py"; // 인자
	      String arg2 = "C:\\\\JAVA\\\\baroyoyak\\\\python\\\\test.txt";
	      ProcessBuilder builder = new ProcessBuilder(command, arg1,arg2);
	      Process process = builder.start();
	      int exitVal = process.waitFor();  // 자식 프로세스가 종료될 때까지 기다림
	      BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), "euc-kr")); // 서브 프로세스가 출력하는 내용을 받기 위해
	      String line;
	      while ((line = br.readLine()) != null) {
	           System.out.println(">>>  " + line); // 표준출력에 쓴다
	           result+=line;
	      }
	      if(exitVal != 0) {
	        // 비정상 종료
	        System.out.println("서브 프로세스가 비정상 종료되었다.");
	      }
	      }catch(Exception e) {
	         System.out.println(e.getMessage());
	      }
	      finally
	      {
	    	  //textfile.delete();
	      }
		
		return result;
	}
}
