package com.pdf.service;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfFormField;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.TextField;
import com.pdf.jdo.CalendarDetails;

public class PdfCalendar {

    public static final String LANGUAGE 			= 	"en";
    public static Properties content 				= 	new Properties();
    public static final String RESOURCE 			=   "src/main/resources/footer.jpg";
    protected Font normal;
    protected Font small;
    protected Font bold;

    enum DaysOfWeek
    {
    	MON, TUE, WEB, THU, FRI, SAT, SUN;
    }

    public PdfCalendar() throws DocumentException, IOException {
  
        normal = new Font(Font.FontFamily.HELVETICA,16);
        small = new Font(Font.FontFamily.TIMES_ROMAN, 8);
        bold = new Font(Font.FontFamily.COURIER, 14,Font.BOLD);
    }

    public File createPdf(HttpServletRequest request, HttpServletResponse response, String fileName, CalendarDetails details) throws IOException, DocumentException {
    	
    	
    	
//    	Properties prop 								= 	new Properties();
//    	prop 											= 	System.getProperties();
    	String home 									=	System.getProperty("user.home");
    	Locale locale 									= 	new Locale(LANGUAGE);
    	File file 										= 	new File(home+"/Downloads/"+fileName);

//    	File file = null;
//    	String resource = this.getClass().getClassLoader().getResource("calendar.pdf").getPath();
//		file = new File(resource);
    	
        Document document 								= 	new Document(PageSize.A4);
        PdfWriter writer 								= 	PdfWriter.getInstance(document, new FileOutputStream(file));
        
        document.open();
        
        PdfContentByte canvas 							= 	writer.getDirectContent();
        PdfPCell cell 									= 	null;
        PdfPTable daysTab 								= 	new PdfPTable(7);
        PdfPCell daysTabCell 							= 	new PdfPCell();
        Font font 										= 	FontFactory.getFont(FontFactory.HELVETICA, 8, BaseColor.WHITE);
        
        PdfPTable table;
        Calendar calendar;
        daysTab.setTotalWidth(515);
        daysTabCell.setBorder(PdfPCell.NO_BORDER);
        
        for(DaysOfWeek days : DaysOfWeek.values())
    	{
    	    cell 										= 	new PdfPCell(new Phrase(days.toString(),font));
    	    BaseColor myColor 							=   com.itextpdf.text.html.WebColors.getRGBColor("#00b8d5");
    	    
    	    cell.setBorder(PdfPCell.NO_BORDER);
    	    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(myColor);
            cell.setPadding(2);
            cell.setFixedHeight(30);
    	    daysTab.addCell(cell);
    	}
    	
        daysTabCell.addElement(daysTab);
        
        int startMonth 									= 	0;
        int endMonth 									=	0;
        Map<Integer,String> map 						= 	getDetails(details.getStartYear(),details.getEndYear());
        Iterator<Entry<Integer, String>> entries 		= 	map.entrySet().iterator();
        boolean same 									= 	false;
        
        while(entries.hasNext())
        {
        	Map.Entry<Integer, String> entry 			= 	(Map.Entry<Integer, String>)entries.next();
        	Integer year 								= 	entry.getKey();
        	String place 								= 	entry.getValue();
        	
        	if(place.equals("start"))
        	{
        		startMonth 								= 	findMonthIndex(details.getFromMonth());
        		endMonth 								= 	11;
        	}
        	else if(place.equals("mid"))
        	{
        		startMonth 								= 	0;
        		endMonth 								= 	11;
        	}
        	else if(place.equals("end"))
        	{
        		startMonth 								= 	0;
        		endMonth 								= 	findMonthIndex(details.getToMonth());
        	}
        	else
        	{
        		startMonth 								= 	findMonthIndex(details.getFromMonth());
        		endMonth 								= 	findMonthIndex(details.getToMonth());
        		same 									= 	true;
        	}
        	
            for (int month = startMonth; month <= endMonth; month++) 
            {
                calendar 								= 	new GregorianCalendar(year, month, 1);
                table 									= 	new PdfPTable(7);
                int daysInMonth	 						= 	calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                BaseColor borderColor					= 	com.itextpdf.text.html.WebColors.getRGBColor("#d5d7da");//d5d7da
                int day 								= 	1;
                int position 							= 	2;
                
                table.setTotalWidth(515);
                table.getDefaultCell().setBackgroundColor(BaseColor.WHITE);
                table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

                while (position != calendar.get(Calendar.DAY_OF_WEEK))
                {
                    position 							= 	(position % 7) + 1;
                    table.addCell("");
                }
                
                while (day <= daysInMonth) 
                {
                    calendar 							= 	new GregorianCalendar(year, month, day++);
                    table.addCell(getDayCell(calendar, locale));
                }
                
                BaseColor monthColor					= 	WebColors.getRGBColor("#0096d0");
                Font monthHeader						= 	FontFactory.getFont(FontFactory.TIMES_ROMAN,70, Font.BOLD, monthColor);
                Paragraph monthHead 					= 	new Paragraph(String.format(locale, "%1$tB", calendar),monthHeader);
             
                BaseColor yearColor						= 	WebColors.getRGBColor("#009ebb");
                Font yearHead							= 	FontFactory.getFont(FontFactory.TIMES_ROMAN,15, Font.NORMAL, yearColor);
                Paragraph yearSub 						= 	new Paragraph(String.format(locale, "%1$tY", calendar),yearHead);
                
                document.add(monthHead);
                document.add(yearSub);
                PdfPTable notes = new PdfPTable(1);
                PdfPCell noteHeaderCell = new PdfPCell();
                Paragraph header = new Paragraph("Notes");
                header.setSpacingAfter(5f);
                noteHeaderCell.addElement(header);
                noteHeaderCell.setBorderColor(BaseColor.WHITE);
                notes.addCell(noteHeaderCell);
                int i=6;
                while(i>0){
                	 PdfPCell noteBodyCell = new PdfPCell();
                     noteBodyCell.setCellEvent(new MyCellField(daysInMonth+""+i));
                     noteBodyCell.setFixedHeight(18f);
                     noteBodyCell.setBorderColor(borderColor);
                     notes.addCell(noteBodyCell);
                     i--;
                }
                notes.setTotalWidth(236f);
                notes.getDefaultCell().setBackgroundColor(BaseColor.WHITE);
                notes.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
                table.completeRow();
                Image img = Image.getInstance("src/main/resources/footer.png");
                if(table.getTotalHeight() > 421){
                	notes.writeSelectedRows(0, -1, 320, table.getTotalHeight()+300, canvas);
                	table.writeSelectedRows(0, -1, 40, table.getTotalHeight()+100, canvas);
                	daysTab.writeSelectedRows(0, 200, 40, table.getTotalHeight()+150, canvas);
                	img.setAbsolutePosition(40f, 60f);
                    img.scaleToFit(520f, 250f);
                } else {
                	notes.writeSelectedRows(0, -1, 320, table.getTotalHeight()+380, canvas);
                	table.writeSelectedRows(0, -1, 40, table.getTotalHeight()+160, canvas);
                	daysTab.writeSelectedRows(0, 200, 40, table.getTotalHeight()+220, canvas);
                	img.setAbsolutePosition(40f, 80f);
                    img.scaleToFit(520f, 250f);
                }
                
                System.out.println(" checking height :: "+table.getTotalHeight());
                
                document.add(img);
                document.newPage();
            }
            
            if(same)
        		break;
        }
        
        document.close();
        response.setContentType("application/pdf");
    	response.setHeader("Content-disposition", "attachment;filename="+fileName);
    	
    	FileInputStream fis 					  	= 	null;
    	DataOutputStream os 					  	= 	null;
    	
    	try
    	{
    		response.setHeader("Content-Length", String.valueOf(file.length()));
    		
    		fis 								 	= 	 new FileInputStream(file);
    		os 										=	 new DataOutputStream(response.getOutputStream());
    		byte[] buffer 							= 	 new byte[1024];
    		int len 								=    0;
    		
    		while((len = fis.read(buffer)) >= 0)
    		{
    			os.write(buffer,0,len);
    		}
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	finally
    	{
    		fis.close();
    		os.flush();
    		os.close();
    	}
//    	response.setHeader("X-Frame-Options", "SAMEORIGIN");
    	return file;
    }
    
    /**
     * Draws the image of the month to the calendar.
     * @param canvas the direct content layer
     * @param calendar the month (to know which picture to use)
     * @throws DocumentException
     * @throws IOException
     */
    public void drawImageAndText(PdfContentByte canvas, Calendar calendar) throws DocumentException, IOException {
        // get the image
    	Image img = Image.getInstance("src/main/resources/footer.png");
    	img.setAbsolutePosition(25f, 50f);
        img.scaleToFit(600f, 200f);
        canvas.addImage(img);
        // add metadata
        canvas.saveState();
        canvas.setCMYKColorFill(0x00, 0x00, 0x00, 0x80);
        Phrase p = new Phrase(String.format(
                "Setmore",
                content.getProperty(String.format("%tm.jpg", calendar))), small);
        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, p, 5, 5, 0);
        canvas.restoreState();
    }
    
    public int findMonthIndex(String month)
    {
    	final String[] monthList 				 	= 	{"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    	String str 									= 	month.substring(0, 3);
    	
    	for(int i=0;i<monthList.length;i++)
    	{
    		if(str.equals(monthList[i]))
    			return i;
    	}   	
    	return -1;
    }
    
    public HashMap<Integer, String> getDetails(Integer startY,Integer endY)
    {
    	HashMap<Integer,String> yearMap 		   =	new HashMap<>();
    	
    	if(startY.equals(endY))
    		yearMap.put(startY, "same");
    	else
    	{
    		for(int i = startY; i <= endY; i++)
        	{
        		if(i == startY)
        			yearMap.put(i, "start");
        		else if(i == endY)
        			yearMap.put(i, "end");
        		else
        			yearMap.put(i, "mid");
        	}
    	}
    	return yearMap;
    }
    
   
    public PdfPCell getMonthCell(Calendar calendar, Locale locale) 
    {
        PdfPCell cell 							 = 	 new PdfPCell();
        
        cell.setColspan(7);
        cell.setBackgroundColor(BaseColor.WHITE);
        cell.setUseDescender(true);
        
        Paragraph p 							= 	new Paragraph(String.format(locale, "%1$tB %1$tY", calendar), bold);
        
        p.setAlignment(Element.ALIGN_CENTER);
        cell.addElement(p);
        
        return cell;
    }

    public PdfPCell getDayCell(Calendar calendar, Locale locale) 
    {
    	PdfPTable daynotes = new PdfPTable(1);
    	PdfPCell cell 						 	= 	new PdfPCell();
        PdfPCell header 						= 	new PdfPCell();
        PdfPCell body 						 	= 	new PdfPCell();
//        Paragraph p 							= 	new Paragraph("");
//        p.add(new Chunk(new VerticalPositionMark()));
        BaseColor dayColor 						=   com.itextpdf.text.html.WebColors.getRGBColor("#536e7a");
        Font font 								= 	FontFactory.getFont(FontFactory.HELVETICA, 10, dayColor);
        BaseColor borderColor					= 	com.itextpdf.text.html.WebColors.getRGBColor("#c4c4c4");
        
//        p.add(new Chunk(String.format(locale, "%1$te", calendar), font));
        Paragraph p = new Paragraph(String.format(locale, "%1$te", calendar));
        p.setSpacingAfter(5f);
        
        header =getCell(String.format(locale, "%1$te", calendar), PdfPCell.ALIGN_RIGHT);
        header.setFixedHeight(17);
        header.setBorderColor(BaseColor.WHITE);
        
        
        
        body.setCellEvent(new MyCellField(locale+""+calendar));
        body.setBackgroundColor(com.itextpdf.text.html.WebColors.getRGBColor("#ffffff"));
        body.setFixedHeight(63);
        body.setBorderColor(BaseColor.WHITE);
        
        daynotes.addCell(header);
        daynotes.addCell(body);
        cell.setBorderColor(borderColor);
        cell.addElement(daynotes);
        return cell;
    }
   
    public PdfPCell getCell(String text, int alignment) {
        PdfPCell cell = new PdfPCell(new Phrase(text));
        cell.setPadding(0);
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }
    
    public boolean isSunday(Calendar calendar) {
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            return true;
        }
        return false;
    }
}

class MyCellField implements PdfPCellEvent {
	
    protected String fieldname;	
    
    public MyCellField(String fieldname) {
        this.fieldname = fieldname;
    }
    
    public void cellLayout(PdfPCell cell, Rectangle rectangle, PdfContentByte[] canvases) {
    	 
        final PdfWriter writer 				= 	canvases[0].getPdfWriter();
        final TextField textField 			= 	new TextField(writer, rectangle,fieldname);
        
        textField.setOptions(TextField.MULTILINE); 
        
        try {
        	
            final PdfFormField field 		= 	textField.getTextField();
            
            writer.addAnnotation(field);
            
        } catch (final IOException ioe) {
            throw new ExceptionConverter(ioe);
        } catch (final DocumentException de) {
            throw new ExceptionConverter(de);
        }
    }
}

