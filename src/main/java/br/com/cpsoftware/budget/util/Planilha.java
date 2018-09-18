package br.com.cpsoftware.budget.util;

import java.io.IOException;
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
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import br.com.cpsoftware.budget.dao.OrcamentoDAO;
import br.com.cpsoftware.budget.dao.ProjetoDAO;
import br.com.cpsoftware.budget.model.Orcamento;
import br.com.cpsoftware.budget.model.Projeto;

public class Planilha {

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
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.BLACK.getIndex());

        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);
        headerCellStyle.setAlignment(HorizontalAlignment.CENTER);

        // Create a Row
        Row headerRow = sheet.createRow(0);

        String[] columns = {"Orçamento", "Valor estimado", "Valor orçado", "Valor realizado"};
        // Create cells
        for(int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
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

		// Resize all columns to fit the content size
        for(int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }
		
        workbook.write(outputStream); // Write workbook to response.
        workbook.close();
        outputStream.close();
	}

}
