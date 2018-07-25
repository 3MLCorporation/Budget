package br.com.cpsoftware.budget.control;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Cell; 
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;

import br.com.cpsoftware.budget.dao.OrcamentoDAO;
import br.com.cpsoftware.budget.dao.ProjetoDAO;
import br.com.cpsoftware.budget.model.Orcamento;
import br.com.cpsoftware.budget.model.Projeto;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class GerarRelatorio extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/pdf");
		this.generatePDF(Long.parseLong((String) req.getAttribute("projetoId")), resp.getOutputStream());
	}
	
	public void generatePDF(Long projetoId, OutputStream out){
		try {
			
			/*Document document = new Document();
			document.setMargins(40, 40, 15, 10);
			PdfWriter.getInstance(document, out);*/
			
			//Initialize PDF writer
	        PdfWriter writer = new PdfWriter(out);
	 
	        //Initialize PDF document
	        PdfDocument pdf = new PdfDocument(writer);
	 
	        // Initialize document
	        Document document = new Document(pdf);
	        
	        ProjetoDAO projetoDao = new ProjetoDAO();
	        OrcamentoDAO orcamentoDao = new OrcamentoDAO();
	        
	        Projeto projeto = (Projeto) projetoDao.read(projetoId);
	        
	        //Add paragraph to the document
			Paragraph p = new Paragraph("Projeto: " + projeto.getNome())
								.setFontSize(24f)
								.setTextAlignment(TextAlignment.CENTER);
			
			//List <Orcamento> orcamentos =  new ArrayList<>();
			//.getOrcamentos(projetoId);
			
			document.add(p);
	 
	        // Add a Paragraph
	        document.add(new Paragraph("iText is:"));
	        // Create a List
	        List list = new List()
	            .setSymbolIndent(12)
	            .setListSymbol("\u2022");
	        // Add ListItem objects
	        list.add(new ListItem("Never gonna give you up"))
	            .add(new ListItem("Never gonna let you down"))
	            .add(new ListItem("Never gonna run around and desert you"))
	            .add(new ListItem("Never gonna make you cry"))
	            .add(new ListItem("Never gonna say goodbye"))
	            .add(new ListItem("Never gonna tell a lie and hurt you"));
	        // Add the list
	        document.add(list);
	        
	     // Creating a table       
	        float [] pointColumnWidths = {1f, 200f, 1f};   
	        Table table = new Table(pointColumnWidths);    
	        
	        // Adding cells to the table       
	        table.addCell(new Cell().add("Name"));       
	        table.addCell(new Cell().add("Raju"));       
	        table.addCell(new Cell().add("Id"));       
	        table.addCell(new Cell().add("1001"));       
	        table.addCell(new Cell().add("Designation"));       
	        table.addCell(new Cell().add("Programmer"));                 
	           
	        // Adding Table to document        
	        document.add(table);                  
	        
	        //Close document
	        document.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addHello(Document document, String message) {
		
	}
	
}