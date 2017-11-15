package com.example.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itextpdf.text.DocumentException;
import com.pdf.jdo.CalendarDetails;
import com.pdf.service.PdfCalendar;

@Controller
public class HelloController {
	
	@RequestMapping("/calendarDetails")
	public String calendarDetails(Model model)
	{
		model.addAttribute("details",new CalendarDetails());
		return "setmore";
	}
	
	@RequestMapping("/pdf/{fileName:.+}")
	public void downloadPDF(HttpServletRequest request,
							HttpServletResponse response,
							@PathVariable("fileName") String fileName,
							@ModelAttribute("details") CalendarDetails details) throws IOException, DocumentException
	{
		new PdfCalendar().createPdf(request, response,fileName,details);
//		File file = new PdfCalendar().createPdf(request, response,fileName,details);
//		response.setContentType("application/octet-stream");
//		response.setContentLength((int) file.length());
//		response.setHeader( "Content-Disposition",
//		         String.format("attachment; filename=\"%s\"", file.getName()));
//		
//		OutputStream out = response.getOutputStream();
//		try (FileInputStream in = new FileInputStream(file)) {
//		    byte[] buffer = new byte[4096];
//		    int length;
//		    while ((length = in.read(buffer)) > 0) {
//		        out.write(buffer, 0, length);
//		    }
//		}
//		out.flush();
	}

}
