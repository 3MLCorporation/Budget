package br.com.cpsoftware.budget.util;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Formatacao {
	
	public static String formatarDinheiro(Double valor) {
		
		Locale locale = new Locale("pt", "BR");
		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
		currencyFormatter.setMinimumFractionDigits(2);
		
		return currencyFormatter.format(valor);
	}
	
	public static String formatarData(Date data) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		String dataFormatada;
		
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		dataFormatada  = (day < 10) ? "0" + day : String.valueOf(day);
		dataFormatada += "/";
		
		int month = calendar.get(Calendar.MONTH) + 1; // JANUARY = 0
		dataFormatada  += (month < 10) ? "0" + month : String.valueOf(month);
		dataFormatada += "/";
		
		dataFormatada += calendar.get(Calendar.YEAR);
		
		return dataFormatada;
	}
	
}
