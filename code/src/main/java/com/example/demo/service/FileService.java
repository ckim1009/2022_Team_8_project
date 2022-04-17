package com.example.demo.service;

import java.awt.Color;
import java.awt.Font;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPageBase;
import com.spire.pdf.graphics.PdfLayoutType;
import com.spire.pdf.graphics.PdfRGBColor;
import com.spire.pdf.graphics.PdfSolidBrush;
import com.spire.pdf.graphics.PdfStringFormat;
import com.spire.pdf.graphics.PdfTextAlignment;
import com.spire.pdf.graphics.PdfTextLayout;
import com.spire.pdf.graphics.PdfTextWidget;
import com.spire.pdf.graphics.PdfTrueTypeFont;

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
	
	
	public File makePDFFile(String file_name, String body) {
		String server_file_name = get_format_time()+file_name;
		
		
		PdfDocument doc = new PdfDocument();
		PdfPageBase page = doc.getPages().add();
		

        //create solid brush objects

        PdfSolidBrush brush1 = new PdfSolidBrush(new PdfRGBColor(Color.BLUE));
        PdfSolidBrush brush2 = new PdfSolidBrush(new PdfRGBColor(Color.BLACK));
 
        //create true type font objects
        PdfTrueTypeFont font1= new PdfTrueTypeFont(new Font("굴림",Font.PLAIN,20));
        PdfTrueTypeFont font2= new PdfTrueTypeFont(new Font("굴림",Font.PLAIN,12));

        
        PdfStringFormat format1 = new PdfStringFormat();
        format1.setAlignment(PdfTextAlignment.Center);
        
        
        if(file_name.length()>30)
        {
        	file_name = file_name.substring(0, 20)+"...의 요약본";
        }
        page.getCanvas().drawString(file_name, font1, brush1, new Point2D.Float((float)page.getActualBounds(true).getWidth()/2, 0),format1);
        
        PdfTextWidget widget = new PdfTextWidget(body, font2, brush2);

        Rectangle2D.Float rect = new Rectangle2D.Float(0, 30, (float)page.getActualBounds(true).getWidth(),(float)page.getActualBounds(true).getHeight());
        
        PdfTextLayout layout = new PdfTextLayout();
        layout.setLayout(PdfLayoutType.Paginate);
        
        widget.draw(page, rect, layout);
        
        doc.saveToFile(folder_name+File.separator+server_file_name+".pdf");
        File pdf_temp = new File(folder_name+File.separator+server_file_name+".pdf");
        
        PDDocument pdfDoc = null;
        //10장이 넘어가면 자르기
        try {
			pdfDoc = PDDocument.load(pdf_temp);
			int pagenum = pdfDoc.getNumberOfPages();
			System.out.println("pagenum1 : "+pagenum);
			if(pagenum>10)
			{
				for(int i = 10; i< pagenum; i++)
				{
					pdfDoc.removePage(i);
				}
				pdfDoc.save(folder_name+File.separator+server_file_name+".pdf");
				
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        finally
        {
        	if(pdfDoc!=null)
				try {
					pdfDoc.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        }
        
        
        return pdf_temp;
	}
}
