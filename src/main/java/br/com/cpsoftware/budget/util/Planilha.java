package br.com.cpsoftware.budget.util;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
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

	private final static String HEADER_STYLE_CATEGORIA = "categoriaHeader";
	private final static String HEADER_STYLE_CATEGORIA_NUMERO = "categoriaNumeroHeader";
	private final static String HEADER_STYLE_CATEGORIA_VALOR= "categoriaValorHeader";
	private final static String HEADER_STYLE_RUBRICA = "rubricaHeader";
	private final static String HEADER_STYLE_RUBRICA_VALOR = "rubricaValorHeader";
	private final static String HEADER_STYLE_ITEM = "itemHeader";
	
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
        
        // Create a Font for styling header cells
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 18);
        headerFont.setColor(IndexedColors.BLACK.getIndex());
        
        Font monthHeaderFont = workbook.createFont();
        monthHeaderFont.setBold(true);
        monthHeaderFont.setColor(IndexedColors.BLACK.getIndex());

        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);
        headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
        
        CellStyle monthHeaderCellStyle = workbook.createCellStyle();
        monthHeaderCellStyle.setFont(monthHeaderFont);
        monthHeaderCellStyle.setAlignment(HorizontalAlignment.CENTER);

        // Create a Row
        Row titleRow = sheet.createRow(0);

        Cell cell = titleRow.createCell(0);
        cell.setCellValue(projeto.getNome());
        cell.setCellStyle(headerCellStyle);
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
        
        Row headerRow = sheet.createRow(5);
        for(int i = 0; i < monthHeadersColumns.length; i++) {
        	sheet.setColumnWidth(i + 4, 256*15);
        	Cell monthHeaderCell = headerRow.createCell(i + 4);
			monthHeaderCell.setCellValue(monthHeadersColumns[i]);
			monthHeaderCell.setCellStyle(monthHeaderCellStyle);
        }

        headerRow.createCell(17).setCellValue("Total");
        sheet.setColumnWidth(17, 256*15);
        headerRow.createCell(18).setCellValue("%");
        sheet.setColumnWidth(18 + 4, 256*15);
        
        sheet.createFreezePane(0, 6);
        
        Map<String, CellStyle> styles = createStyles(workbook);
        
        for(Orcamento orcamento : orcamentos) {
			addOrcamento(sheet, styles, orcamento);
		}

        /*// Create Cell Style for formatting Date
        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

        // Create Other rows and cells with employees data
        int rowNum = 1;
        for(Employee employee: employees) {
            Row row = sheet.createRow(rowNum++);

            row.createCell(0)
                    .setCellValue(employee.getName());

            row.createCell(1)
                    .setCellValue(employee.getEmail());

            Cell dateOfBirthCell = row.createCell(2);
            dateOfBirthCell.setCellValue(employee.getDateOfBirth());
            dateOfBirthCell.setCellStyle(dateCellStyle);

            row.createCell(3)
                    .setCellValue(employee.getSalary());
        }*/

		/*// Resize all columns to fit the content size
        for(int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }*/
		
        workbook.write(outputStream); // Write workbook to response.
        workbook.close();
        outputStream.close();
	}

	private static void addOrcamento(Sheet sheet, Map<String, CellStyle> styles, Orcamento orcamento) {
		//sheet.autoSizeColumn(0);
		for(Categoria categoria : categoriaDAO.getCategorias(orcamento.getId())) {
			addCategoria(sheet, styles, categoria);
		}
	}
	

	private static void addCategoria(Sheet sheet, Map<String, CellStyle> styles, Categoria categoria) {
		Row categoriaRow = sheet.createRow(sheet.getLastRowNum() + 1);
		Cell categoriaCell = categoriaRow.createCell(2);
		categoriaCell.setCellValue(categoria.getNome().toUpperCase());
		categoriaCell.setCellStyle(styles.get(HEADER_STYLE_CATEGORIA));
		String formula = "";
		for(Rubrica rubrica : rubricaDAO.getRubricas(categoria.getId())) {
			formula = addRubrica(sheet, styles, rubrica);
		}
		for(int i = 4; i < 20; i++) {
			categoriaRow.createCell(i).setCellStyle(styles.get(HEADER_STYLE_CATEGORIA_VALOR));
		}
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

	private static String addRubrica(Sheet sheet, Map<String, CellStyle> styles, Rubrica rubrica) {
		Row rubricaRow = sheet.createRow(sheet.getLastRowNum() + 1);
		Cell rubricaCell = rubricaRow.createCell(2);
		rubricaCell.setCellValue(rubrica.getNome());
		rubricaCell.setCellStyle(styles.get(HEADER_STYLE_CATEGORIA));
		for(Item item : itemDAO.getItens(rubrica.getId())) {
			addItem(sheet, styles, item);
		}
		for(int i = 4; i < 20; i++) {
			rubricaRow.createCell(i).setCellStyle(styles.get(HEADER_STYLE_RUBRICA_VALOR));
		}
		return "dummy_data";
	}

	private static void addItem(Sheet sheet, Map<String, CellStyle> styles, Item item) {
		Row itemRow = sheet.createRow(sheet.getLastRowNum() + 1);
		itemRow.createCell(3).setCellValue(item.getNome());

		Calendar cal = Calendar.getInstance();
		
		for(NotaFiscal nota :notaFiscalDAO.getNotasFiscais(item.getId())) {
			cal.setTime(nota.getData());
			int mes = cal.get(Calendar.MONTH);
			setMonthCellValue(itemRow, mes + 4, nota.getValor());
			/*
			 * Tanto código e tempo pra no final dar 10 linhas
			 * 
			 * switch(cal.get(Calendar.MONTH)) {
			case Calendar.JANUARY:
				setMonthCellValue(itemRow, JANEIRO_COLUNA, nota.getValor());
				break;
			case Calendar.FEBRUARY:
				setMonthCellValue(itemRow, JANEIRO_COLUNA, nota.getValor());
				break;
			case Calendar.MARCH:
				setMonthCellValue(itemRow, JANEIRO_COLUNA, nota.getValor());
				break;
			case Calendar.APRIL:
				setMonthCellValue(itemRow, JANEIRO_COLUNA, nota.getValor());
				break;
			case Calendar.MAY:
				setMonthCellValue(itemRow, JANEIRO_COLUNA, nota.getValor());
				break;
			case Calendar.JUNE:
				setMonthCellValue(itemRow, JANEIRO_COLUNA, nota.getValor());
				break;
			case Calendar.JULY:
				setMonthCellValue(itemRow, JANEIRO_COLUNA, nota.getValor());
				break;
			case Calendar.AUGUST:
				setMonthCellValue(itemRow, JANEIRO_COLUNA, nota.getValor());
				break;
			case Calendar.SEPTEMBER:
				setMonthCellValue(itemRow, JANEIRO_COLUNA, nota.getValor());
				break;
			case Calendar.OCTOBER:
				setMonthCellValue(itemRow, JANEIRO_COLUNA, nota.getValor());
				break;
			case Calendar.NOVEMBER:
				setMonthCellValue(itemRow, JANEIRO_COLUNA, nota.getValor());
				break;
			case Calendar.DECEMBER:
				setMonthCellValue(itemRow, JANEIRO_COLUNA, nota.getValor());
				break;
			}*/
		}
	}

	private static void setMonthCellValue(Row itemRow, int mes, Double valor){
		if(itemRow.getCell(mes) == null) {
			itemRow.createCell(mes).setCellValue(valor);
		}else {
			Double valorAnterior = itemRow.getCell(mes).getNumericCellValue();
			itemRow.getCell(mes).setCellValue(valorAnterior + valor);
		}
	}
	
	private static Map<String, CellStyle> createStyles(Workbook wb){
		Map<String, CellStyle> styles = new HashMap<>();
		DataFormat df = wb.createDataFormat();
		
		CellStyle style = wb.createCellStyle();
		Font headerFont = wb.createFont();
        headerFont.setBold(true);
		style.setFont(headerFont);
        style.setAlignment(HorizontalAlignment.LEFT);
        styles.put(HEADER_STYLE_CATEGORIA_NUMERO, style);
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
        style.setFont(fontValor);
        style.setDataFormat(df.getFormat("R$ #,#0.00"));
        styles.put(HEADER_STYLE_RUBRICA_VALOR, style);
        
        return styles;
	}
}
