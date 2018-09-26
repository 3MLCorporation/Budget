package br.com.cpsoftware.budget.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.StylesDocument;

import br.com.cpsoftware.budget.dao.CategoriaDAO;
import br.com.cpsoftware.budget.dao.FornecedorDAO;
import br.com.cpsoftware.budget.dao.ItemDAO;
import br.com.cpsoftware.budget.dao.NotaFiscalDAO;
import br.com.cpsoftware.budget.dao.OrcamentoDAO;
import br.com.cpsoftware.budget.dao.PagamentoDAO;
import br.com.cpsoftware.budget.dao.ProjetoDAO;
import br.com.cpsoftware.budget.dao.RubricaDAO;
import br.com.cpsoftware.budget.model.Categoria;
import br.com.cpsoftware.budget.model.Item;
import br.com.cpsoftware.budget.model.NotaFiscal;
import br.com.cpsoftware.budget.model.Orcamento;
import br.com.cpsoftware.budget.model.Projeto;
import br.com.cpsoftware.budget.model.Rubrica;

public class Planilha {

	private static ProjetoDAO projetoDAO = new ProjetoDAO();
	private static OrcamentoDAO orcamentoDAO = new OrcamentoDAO();
	private static CategoriaDAO categoriaDAO = new CategoriaDAO();
	private static RubricaDAO rubricaDAO = new RubricaDAO();
	private static ItemDAO itemDAO = new ItemDAO();
	private static FornecedorDAO fornecedorDAO = new FornecedorDAO();
	private static NotaFiscalDAO notaFiscalDAO = new NotaFiscalDAO();
	private static PagamentoDAO pagamentoDAO = new PagamentoDAO();

	private final static int TOTAL_COLUNA = 17;
	private final static int PORCENTAGEM_COLUNA = 18;
	
	private final static int JANEIRO_COLUNA = 1;
	private final static int FEVEREIRO_COLUNA = 2;
	private final static int MARÇO_COLUNA = 3;
	private final static int ABRIL_COLUNA = 4;
	private final static int MAIO_COLUNA = 5;
	private final static int JUNHO_COLUNA = 6;
	private final static int JULHO_COLUNA = 7;
	private final static int AGOSTO_COLUNA = 8;
	private final static int SETEMBRO_COLUNA = 9;
	private final static int OUTUBRO_COLUNA = 10;
	private final static int NOVEMBRO_COLUNA = 11;
	private final static int DEZEMBRO_COLUNA = 12;

	//TODO Melhorar esses nomes
	private static final String HEADER_STYLE_TITLE = "titleHeader";
	private static final String HEADER_STYLE_MES_VALOR = "mesEValorHeader";
	private final static String HEADER_STYLE_CATEGORIA = "categoriaHeader";
	private final static String HEADER_STYLE_CATEGORIA_NAT = "categoriaNaturezaHeader";
	private final static String HEADER_STYLE_CATEGORIA_VALOR= "categoriaValorHeader";
	private final static String HEADER_STYLE_RUBRICA = "rubricaHeader";
	private final static String HEADER_STYLE_RUBRICA_VALOR = "rubricaValorHeader";
	private final static String HEADER_STYLE_ITEM = "itemHeader";
	private final static String HEADER_STYLE_ITEM_NAT = "itemNaturezaHeader";
	private static final String HEADER_STYLE_ITEM_VALOR = "itemValorHeader";
	
	public static void gerarPlanilha(Long projetoId, ServletOutputStream outputStream) throws IOException {
		
		Projeto projeto = (Projeto) new ProjetoDAO().read(projetoId);
		
		List<Orcamento> orcamentos = new OrcamentoDAO().getOrcamentos(projetoId);
		
		
		// Create a Workbook
        Workbook workbook = new XSSFWorkbook();

        /* CreationHelper helps us create instances of various things like DataFormat, 
           Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way */
        CreationHelper createHelper = workbook.getCreationHelper();

        // Create a Sheet
        Sheet sheet = workbook.createSheet(orcamentos.get(0).getNome());

        Map<String, CellStyle> styles = createStyles(workbook);
        
        

        // Create a Row
        Row titleRow = sheet.createRow(0);

        Cell cell = titleRow.createCell(0);
        cell.setCellValue(projeto.getNome());
        cell.setCellStyle(styles.get(HEADER_STYLE_TITLE));
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 19));
        
        String[] headerColumn = {"CURSO/SETOR" , "CENTRO DE CUSTO", "RESPONSÁVEL PELO PREENCHIMENTO", "APROVADOR(A)"};
        String[] monthHeadersColumns = {"JANEIRO", "FEVEREIRO", "MARÇO", "ABRIL", "MAIO", "JUNHO", "JULHO",
        		"AGOSTO", "SETEMBRO", "OUTUBRO", "NOVEMBRO", "DEZEMBRO"
        };
        
        for(int i = 1; i < 5; i++) {
        	sheet.createRow(i).createCell(4).setCellValue(headerColumn[i - 1]);
        }

        sheet.setColumnWidth(0, 256*2);
        sheet.setColumnWidth(1, 256*8);
        sheet.setColumnWidth(2, 256*2);
        sheet.setColumnWidth(3, 256*40);
        sheet.setColumnWidth(17, 256*15);
        
        Row headerRow = sheet.createRow(5);
        for(int i = 0; i < monthHeadersColumns.length; i++) {
        	sheet.setColumnWidth(i + 4, 256*15);
        	Cell monthHeaderCell = headerRow.createCell(i + 4);
			monthHeaderCell.setCellValue(monthHeadersColumns[i]);
			monthHeaderCell.setCellStyle(styles.get(HEADER_STYLE_MES_VALOR));
        }

        sheet.setColumnWidth(17, 256*15);
        Cell valorTotalHeaderCell = headerRow.createCell(17);
        valorTotalHeaderCell.setCellValue("TOTAL");
        valorTotalHeaderCell.setCellStyle(styles.get(HEADER_STYLE_MES_VALOR));

        sheet.setColumnWidth(18 + 4, 256*15);
        Cell porcentagemHeaderCell = headerRow.createCell(18);
        porcentagemHeaderCell.setCellValue("%");
        porcentagemHeaderCell.setCellStyle(styles.get(HEADER_STYLE_MES_VALOR));
        
        sheet.createFreezePane(0, 6);
        
        for(Orcamento orcamento : orcamentos) {
			addOrcamento(sheet, styles, orcamento);
		}
		
        workbook.write(outputStream); // Write workbook to response.
        workbook.close();
        outputStream.close();
	}

	private static void addOrcamento(Sheet sheet, Map<String, CellStyle> styles, Orcamento orcamento) {
		//sheet.autoSizeColumn(0);
		int natOrc = 1;
		for(Categoria categoria : categoriaDAO.getCategorias(orcamento.getId())) {
			addCategoria(sheet, styles, categoria, natOrc);
			natOrc++;
		}
	}
	

	private static void addCategoria(Sheet sheet, Map<String, CellStyle> styles, Categoria categoria, int naturezaOrcamentaria) {
		Row categoriaRow = sheet.createRow(sheet.getLastRowNum() + 2);
		
		Cell naturezaOrcamentariaCell = categoriaRow.createCell(1);
		naturezaOrcamentariaCell.setCellValue(naturezaOrcamentaria);
		naturezaOrcamentariaCell.setCellStyle(styles.get(HEADER_STYLE_CATEGORIA_NAT));
		
		Cell categoriaCell = categoriaRow.createCell(2);
		categoriaCell.setCellValue(categoria.getNome().toUpperCase());
		categoriaCell.setCellStyle(styles.get(HEADER_STYLE_CATEGORIA));
		
		List<Integer> rubricasRowNumbers = new ArrayList<>();
		
		int qtd = 0;
		String rubricaNatOrc;
		for(Rubrica rubrica : rubricaDAO.getRubricas(categoria.getId())) {
			qtd++;
			if(qtd > 9) {
				rubricaNatOrc = naturezaOrcamentaria + "." + qtd;
			}else {
				rubricaNatOrc = naturezaOrcamentaria + ".0" + qtd;
			}
			rubricasRowNumbers.add(addRubrica(sheet, styles, rubrica, rubricaNatOrc));
		}
		
		Cell categoriaValorCell;
		for(int i = 4; i < 18; i++) {
			if(i != 16) {//Pula a coluna 16
				categoriaValorCell = categoriaRow.createCell(i);
				categoriaValorCell.setCellStyle(styles.get(HEADER_STYLE_CATEGORIA_VALOR));
				categoriaValorCell.setCellType(CellType.FORMULA);
				
				Map<Integer, Character> columnsStringsByNumbers = getColumnsStringsByNumbers();
				String formula = "";
				for(int rubricaRowNumber : rubricasRowNumbers) {
					formula += columnsStringsByNumbers.get(i).toString() + rubricaRowNumber + "+";// ex.: E10+E27+E31
				}
				
				//Remove o último "+"
				if (formula != null && formula.length() > 0 && formula.charAt(formula.length() - 1) == '+') {
			        formula = formula.substring(0, formula.length() - 1);
			    }
				
				categoriaValorCell.setCellFormula(formula);
			}
		}
		
		Cell categoriaTotal = categoriaRow.createCell(18);
		//TODO CONTINUAR, MSM LOGICA DOS OUTROS VALORES, COMEÇANDO DE BAIXO
		//TODO MUDAR O TAMANHO DAS COLUNAS E BOTAR BORDA BRANCA TBM
	}
	
	/*private static CellStyle getStyle(Workbook workbook, int styleType) {
		switch(styleType) {
		case HEADER_STYLE_CATEGORIA:
			CellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setFont(headerFont);
			cellStyle.setAlignment(HorizontalAlignment.CENTER);
			break;
		}
	}*/

	private static int addRubrica(Sheet sheet, Map<String, CellStyle> styles, Rubrica rubrica, String rubricaNatOrc) {
		Row rubricaRow = sheet.createRow(sheet.getLastRowNum() + 1);
		
		Cell naturezaOrcamentariaCell = rubricaRow.createCell(1);
		naturezaOrcamentariaCell.setCellValue(rubricaNatOrc);
		naturezaOrcamentariaCell.setCellStyle(styles.get(HEADER_STYLE_CATEGORIA_NAT));
		
		Cell rubricaCell = rubricaRow.createCell(2);
		rubricaCell.setCellValue(rubrica.getNome());
		rubricaCell.setCellStyle(styles.get(HEADER_STYLE_CATEGORIA));
		
		List<Item> itens = itemDAO.getItens(rubrica.getId());
		int qtd = 0;
		String itemNatOrc;
		for(Item item : itens) {
			qtd++;
			if(qtd > 9) {
				itemNatOrc = rubricaNatOrc + ".0" + qtd;
			}else {
				itemNatOrc = rubricaNatOrc + ".00" + qtd;
			}
			addItem(sheet, styles, item, itemNatOrc);
		}
		
		Cell rubricaCellValor;
		for(int i = 4; i < 18; i++) {
			if(i != 16) {//Pula a coluna 16
				rubricaCellValor = rubricaRow.createCell(i);
				rubricaCellValor.setCellStyle(styles.get(HEADER_STYLE_RUBRICA_VALOR));
				if(itens.size() > 0) {
					rubricaCellValor.setCellType(CellType.FORMULA);
					String rubricaColumn = rubricaCellValor.getAddress().formatAsString().substring(0, 1);
					//rubricaRow.getRowNum() != rubricaCellValor.getAddress().getRow()
					//a diferença é pq a primeira linha do primeiro método é 0
					int rubricaRowNum = rubricaRow.getRowNum() + 1;
					String firstCell = rubricaColumn + (rubricaRowNum + 1);
					String lastCell =  rubricaColumn + (rubricaRowNum + itens.size());
					rubricaCellValor.setCellFormula("SUM(" +  firstCell + ":" + lastCell + ")");// ex.: SUM(E10:E27)
				}
			}
		}
		
		/*Cell rubricaTotalCell = rubricaRow.createCell(TOTAL_COLUNA);
		rubricaTotalCell.setCellStyle(styles.get(HEADER_STYLE_RUBRICA_VALOR));
		rubricaTotalCell.setCellType(CellType.FORMULA);
		String rubricaColumn = rubricaTotalCell.getAddress().formatAsString().substring(0, 1);
		int rubricaRowNum = rubricaRow.getRowNum() + 1;
		String firstCell = rubricaColumn + (rubricaRowNum + 1);
		String lastCell =  rubricaColumn + (rubricaRowNum + itens.size());
		rubricaTotalCell.setCellFormula("SUM(" +  firstCell + ":" + lastCell + ")");// ex.: SUM(E10:E27)*/
		
		//Vai no formato certo pra fórmula
		return rubricaRow.getRowNum() + 1;
	}

	private static void addItem(Sheet sheet, Map<String, CellStyle> styles, Item item, String itemNatOrc) {
		Row itemRow = sheet.createRow(sheet.getLastRowNum() + 1);
		
		Cell naturezaOrcamentariaCell = itemRow.createCell(1);
		naturezaOrcamentariaCell.setCellValue(itemNatOrc);
		naturezaOrcamentariaCell.setCellStyle(styles.get(HEADER_STYLE_ITEM_NAT));
		
		itemRow.createCell(3).setCellValue(item.getNome());

		Calendar cal = Calendar.getInstance();
		
		for(NotaFiscal nota :notaFiscalDAO.getNotasFiscais(item.getId())) {
			cal.setTime(nota.getData());
			int mes = cal.get(Calendar.MONTH);
			setMonthCellValue(itemRow, styles, mes + 4, nota.getValor());
			
		}
		
		Cell valorTotalCell = itemRow.createCell(17);
		valorTotalCell.setCellType(CellType.FORMULA);
		valorTotalCell.setCellStyle(styles.get(HEADER_STYLE_ITEM_VALOR));
		Map<Integer, Character> columnsStringsByNumbers = getColumnsStringsByNumbers();
		int itemRowNum = itemRow.getRowNum() + 1;
		String firstCell = columnsStringsByNumbers.get(4).toString() + itemRowNum;
		String lastCell =  columnsStringsByNumbers.get(15).toString() + itemRowNum;
		valorTotalCell.setCellFormula("SUM(" +  firstCell + ":" + lastCell + ")");
	}

	private static void setMonthCellValue(Row itemRow, Map<String, CellStyle> styles,int mes, Double valor){
		if(itemRow.getCell(mes) == null) {
			Cell itemValorCell = itemRow.createCell(mes);
			itemValorCell.setCellValue(valor);
			itemValorCell.setCellStyle(styles.get(HEADER_STYLE_ITEM_VALOR));
		}else {
			Double valorAnterior = itemRow.getCell(mes).getNumericCellValue();
			itemRow.getCell(mes).setCellValue(valorAnterior + valor);
		}
	}
	
	private static Map<Integer, Character> getColumnsStringsByNumbers(){
		Map<Integer, Character> columnsStringsByNumbers = new HashMap<>();
		char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toUpperCase().toCharArray();
		//columnsStringsByNumbers.put(0, "A");
		
		for(int i = 0; i < alphabet.length; i++) {
			columnsStringsByNumbers.put(i, alphabet[i]);
		}
		return columnsStringsByNumbers;
	}
	
	private static Map<String, CellStyle> createStyles(Workbook wb){
		Map<String, CellStyle> styles = new HashMap<>();
		DataFormat df = wb.createDataFormat();
		
		CellStyle style = wb.createCellStyle();
		
        Font headerFont = wb.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 18);
        headerFont.setColor(IndexedColors.BLACK.getIndex());
        style.setFont(headerFont);
        style.setAlignment(HorizontalAlignment.CENTER);
        styles.put(HEADER_STYLE_TITLE, style);
        
        
        Font monthHeaderFont = wb.createFont();
        monthHeaderFont.setBold(true);
        monthHeaderFont.setColor(IndexedColors.BLACK.getIndex());
        style = wb.createCellStyle();
        style.setFont(monthHeaderFont);
        style.setAlignment(HorizontalAlignment.CENTER);
        styles.put(HEADER_STYLE_MES_VALOR, style);
        
		Font headerCategoriaFont = wb.createFont();
        headerCategoriaFont.setBold(true);
        style = wb.createCellStyle();
		style.setFont(headerCategoriaFont);
        style.setAlignment(HorizontalAlignment.LEFT);
        styles.put(HEADER_STYLE_CATEGORIA_NAT, style);
        styles.put(HEADER_STYLE_CATEGORIA, style);
        
        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        Font fontValor = wb.createFont();
        fontValor.setColor(IndexedColors.BLUE.getIndex());
        fontValor.setBold(true);
        style.setFont(fontValor);
        style.setDataFormat(df.getFormat("R$ #,#0.00"));
        styles.put(HEADER_STYLE_CATEGORIA_VALOR, style);
        
        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        Font fontRubricaValor = wb.createFont();
        fontRubricaValor.setColor(IndexedColors.BLACK.getIndex());
        fontRubricaValor.setBold(true);
        style.setFont(fontRubricaValor);
        style.setDataFormat(df.getFormat("R$ #,#0.00"));
        styles.put(HEADER_STYLE_RUBRICA_VALOR, style);
        
        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.LEFT);
        styles.put(HEADER_STYLE_ITEM_NAT, style);
        
        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setDataFormat(df.getFormat("R$ #,#0.00"));
        styles.put(HEADER_STYLE_ITEM_VALOR, style);
        
        return styles;
	}
}
