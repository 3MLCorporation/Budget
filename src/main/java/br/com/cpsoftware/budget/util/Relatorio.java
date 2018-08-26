package br.com.cpsoftware.budget.util;

import java.util.List;

import javax.servlet.ServletOutputStream;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

import br.com.cpsoftware.budget.dao.CategoriaDAO;
import br.com.cpsoftware.budget.dao.FornecedorDAO;
import br.com.cpsoftware.budget.dao.ItemDAO;
import br.com.cpsoftware.budget.dao.NotaFiscalDAO;
import br.com.cpsoftware.budget.dao.OrcamentoDAO;
import br.com.cpsoftware.budget.dao.PagamentoDAO;
import br.com.cpsoftware.budget.dao.ProjetoDAO;
import br.com.cpsoftware.budget.dao.RubricaDAO;
import br.com.cpsoftware.budget.model.Categoria;
import br.com.cpsoftware.budget.model.Fornecedor;
import br.com.cpsoftware.budget.model.Item;
import br.com.cpsoftware.budget.model.NotaFiscal;
import br.com.cpsoftware.budget.model.Orcamento;
import br.com.cpsoftware.budget.model.Pagamento;
import br.com.cpsoftware.budget.model.Rubrica;

public class Relatorio {

	private static final int NUMERO_COLUNAS = 10;
	private static final String HEADERS[] = {
			"MATERIAIS OU SERVIÇOS",
			"FORNECEDOR",
			"CNPJ Nº",
			"UF",
			"NOTA FISCAL ",
			"COMPROVANTE DE PAGAMENTO 1"};
	private static final String SUB_HEADERS[] = {"VALOR", "DATA", "NÚMERO", "VALOR", "DATA", "TIPO"};
	
	private static ProjetoDAO projetoDAO = new ProjetoDAO();
	private static OrcamentoDAO orcamentoDAO = new OrcamentoDAO();
	private static CategoriaDAO categoriaDAO = new CategoriaDAO();
	private static RubricaDAO rubricaDAO = new RubricaDAO();
	private static ItemDAO itemDAO = new ItemDAO();
	private static FornecedorDAO fornecedorDAO = new FornecedorDAO();
	private static NotaFiscalDAO notaFiscalDAO = new NotaFiscalDAO();
	private static PagamentoDAO pagamentoDAO = new PagamentoDAO();
	
	public static void generatePDF(long projetoId, ServletOutputStream out) {
		try {
	        PdfWriter writer = new PdfWriter(out);
	        PdfDocument pdf = new PdfDocument(writer);
	        Document document = new Document(pdf, PageSize.A1.rotate());
	        
	        Paragraph p = new Paragraph("Projeto: " + projetoDAO.read(projetoId).getNome())
					.setFontSize(24f)
					.setTextAlignment(TextAlignment.JUSTIFIED).setMarginBottom(0);
	        Paragraph p2 = new Paragraph("Orçamento: " + "Construção civil")
					.setFontSize(18f)
					.setTextAlignment(TextAlignment.JUSTIFIED).setMarginTop(0);
			
	        document.add(p);
	        document.add(p2);
	        
			Table table = new Table(NUMERO_COLUNAS).setAutoLayout();
			
			addHeader(table);
			
			for(Orcamento orcamento : orcamentoDAO.getOrcamentos(projetoId)) {
				for(Categoria categoria : categoriaDAO.getCategorias(orcamento.getId())) {
					for(Rubrica rubrica : rubricaDAO.getRubricas(categoria.getId())) {
						addRubrica(table, rubrica);
					}
				}
			}
			
	        document.add(table);               
	        document.close();
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void addRubrica(Table table, Rubrica rubrica) {
		for (int i = 0; i < NUMERO_COLUNAS; i++) {
			Cell cell = new Cell(1, 1)
					.setTextAlignment(TextAlignment.CENTER).setFontSize(14f).setBold().setPadding(10F)
					.setBackgroundColor(Color.LIGHT_GRAY);
			if(i == 0) {
				table.addCell(cell.add(rubrica.getNome()).setBorderRight(Border.NO_BORDER));
			}else if(i == NUMERO_COLUNAS - 1 || i == 3 || i == 6){
				table.addCell(cell.add("").setBorderLeft(Border.NO_BORDER));
			}else {
				table.addCell(cell.add("").setBorderLeft(Border.NO_BORDER).setBorderRight(Border.NO_BORDER));
			}
		}
		
		List<Item> itens = itemDAO.getItens(rubrica.getId());
		
		double subTotalNF = 0;
		double subTotalPg = 0;
		for (Item item : itens) {
			for(NotaFiscal nota : notaFiscalDAO.getNotasFiscais(item.getId())) {
				
				table.addCell(item.getNome());
				
				Fornecedor fornecedor = fornecedorDAO.read(nota.getFornecedorId());
				table.addCell(fornecedor.getRazaoSocial());
				table.addCell(fornecedor.getCnpj());
				table.addCell(fornecedor.getUf());
				
				table.addCell(nota.getValorFormatado());
				subTotalNF += nota.getValor();
				table.addCell(nota.getDataFormatada());
				//TODO Substituir pelo numero da nota fiscal - table.addCell(nota.getNumero());
				table.addCell(String.valueOf(nota.getId()));
				
				List<Pagamento> pagamentos = pagamentoDAO.getPagamentos(nota.getId());
				if(!pagamentos.isEmpty()) {
					Pagamento pagamento = pagamentos.get(0);
					table.addCell(pagamento.getValorFormatado());
					subTotalPg += pagamento.getValor();
					table.addCell(pagamento.getDataFormatada());
					//TODO Substituir pelo tipo de pagamento - pagamento.getTipo() (?)
					table.addCell(String.valueOf(pagamento.getId()));
				}else {
					addEmptyCells(table, 3);
				}
			}
		} 
		table.addCell("Sub Total");
		addEmptyCells(table, 3);
		table.addCell(Formatacao.formatarDinheiro(subTotalNF));
		//TODO Substituir por colspan, depois
		addEmptyCells(table, 2);
		table.addCell(Formatacao.formatarDinheiro(subTotalPg));
		addEmptyCells(table, 2);
		
	}

	private  static void addHeader(Table table) {
		for (int i = 0; i < 4; i++) {
			Cell cell = new Cell(2, 1)
					.setBold()
					.setFontSize(14f)
					.setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE);
			table.addHeaderCell(cell.add(HEADERS[i]));
		}
		
		for(int i = 4; i < HEADERS.length; i++) {
			Cell cell = new Cell(1, 3)
					.setBold()
					.setFontSize(14f)
					.setTextAlignment(TextAlignment.CENTER);
			table.addHeaderCell(cell.add(HEADERS[i]));
		}
		
		
		for (int i = 0; i < SUB_HEADERS.length; i++) {
			Cell cell = new Cell(1, 1)
					.setBold()
					.setFontSize(14f)
					.setTextAlignment(TextAlignment.CENTER);
			table.addHeaderCell(cell.add(SUB_HEADERS[i]));
		}
	}
	
	private static void addEmptyCells(Table table, int qtd) {
		for(int i = 0; i < qtd; i++) {
			table.addCell("");
		}
	}
}
