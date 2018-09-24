package br.com.cpsoftware.budget.util;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
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

	private static int JANEIRO_COLUNA = 1;
	private static int FEVEREIRO_COLUNA = 2;
	private static int MARÇO_COLUNA = 3;
	private static int ABRIL_COLUNA = 4;
	private static int MAIO_COLUNA = 5;
	private static int JUNHO_COLUNA = 6;
	private static int JULHO_COLUNA = 7;
	private static int AGOSTO_COLUNA = 8;
	private static int SETEMBRO_COLUNA = 9;
	private static int OUTUBRO_COLUNA = 10;
	private static int NOVEMBRO_COLUNA = 11;
	private static int DEZEMBRO_COLUNA = 12;
	
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
        
        for(Orcamento orcamento : orcamentos) {
			addOrcamento(sheet, orcamento);
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

	private static void addOrcamento(Sheet sheet, Orcamento orcamento) {
		
		Row orcamentoRow = sheet.createRow(sheet.getLastRowNum() + 1);
		orcamentoRow.createCell(0).setCellValue(orcamento.getNome());
		//sheet.autoSizeColumn(0);
		for(Categoria categoria : categoriaDAO.getCategorias(orcamento.getId())) {
			Row categoriaRow = sheet.createRow(sheet.getLastRowNum() + 1);
			categoriaRow.createCell(0).setCellValue(categoria.getNome());
			for(Rubrica rubrica : rubricaDAO.getRubricas(categoria.getId())) {
				Row rubricaRow = sheet.createRow(sheet.getLastRowNum() + 1);
				rubricaRow.createCell(0).setCellValue(rubrica.getNome());
				for(Item item : itemDAO.getItens(rubrica.getId())) {
					Row itemRow = sheet.createRow(sheet.getLastRowNum() + 1);
					itemRow.createCell(0).setCellValue(item.getNome());

					Calendar cal = Calendar.getInstance();
					
					for(NotaFiscal nota :notaFiscalDAO.getNotasFiscais(item.getId())) {
						cal.setTime(nota.getData());
						int mes = cal.get(Calendar.MONTH);
						setMonthCellValue(itemRow, mes + 1, nota.getValor());
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
			}
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

}
