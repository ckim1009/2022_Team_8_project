package com.example.demo.service;

import java.io.File;
import org.springframework.stereotype.Service;

import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPageBase;
import com.spire.pdf.widget.PdfPageCollection;

@Service
public class PDFService {

	public String getText_spire(File result_file )
	{
		
		String text="";
		
		//if(file!=null && file.getOriginalFilename().contains(".pdf"))
		{
			try {
				System.out.println("파일이름 : "+result_file.getName());
				PdfDocument doc = new PdfDocument();
				doc.loadFromFile("temp"+File.separator+result_file.getName());
				PdfPageCollection pages = doc.getPages();
				
				StringBuilder sb=new StringBuilder();

				
				for(int i=0; i<pages.getCount();i++)
				{
					PdfPageBase page = pages.get(i);
					//SimpleTextExtractionStrategy strategy = new SimpleTextExtractionStrategy();
					text += page.extractText(true);
					sb.append(text);
				}
			/*	
			FileWriter writer = new FileWriter("temp"+File.separator+"result.txt");
				
			writer.write(sb.toString());
				
			writer.flush();
			*/
			doc.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		result_file.delete();
		
		return text;
	}
}
