package br.com.cpsoftware.budget.control;

import javax.servlet.http.HttpServlet;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@SuppressWarnings("serial")
public class GerarRelatorio extends HttpServlet {
	
	public static final String DEST = "Relatorio.pdf";
	
	public static void main(String args[]) throws IOException{
		File file = new File(DEST);
		file.getParentFile().mkdirs();
		new GerarRelatorio().createPdf(DEST);
	}
	
	private void createPdf(String dest) throws IOException {
		//Initialize PDF Writer
		FileOutputStream fos = new FileOutputStream(dest);
		PdfWriter writer = new PdfWriter(fos);
		
		//Initialize PDF document
		PdfDocument pdf = new PdfDocument(writer);
		
		//Initialize document
		Document document = new Document(pdf);
		
		//Add paragraph to the document
		document.add(new Paragraph("Hello World!"));
		
		//Close document
		document.close();
	}
	
}