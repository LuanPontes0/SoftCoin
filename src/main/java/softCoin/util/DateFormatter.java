package softCoin.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.google.protobuf.TextFormat.ParseException;

public class DateFormatter {

	private static final String FORMATO_DATA = "yyyy/MM/dd";
	private static final String FORMATO_DATA_BR = "dd/MM/yyyy";
	private static final int IDADE_MINIMA = 18;
	private static final int IDADE_MAXIMA = 150;

	public String formatarData(Date data) {
		SimpleDateFormat formatador = new SimpleDateFormat(FORMATO_DATA);
		return formatador.format(data);
	}

	public String formatarDataBR(Date data) {
		SimpleDateFormat formatador = new SimpleDateFormat(FORMATO_DATA_BR);
		return formatador.format(data);
	}

	public Date converterParaData(String dataStr) {
		SimpleDateFormat formatador = new SimpleDateFormat(FORMATO_DATA);
		char[] dataPartida = dataStr.toCharArray();
		String dia = "", mes = "", ano = "";
		for (int i = 0; i < dataPartida.length; i++) {
			if (i < 2) {
				dia += dataPartida[i];
			}
			if (i >= 2 && i < 6) {
				mes += dataPartida[i];
			}
			if (i >= 6) {
				ano += dataPartida[i];
			}

		}

		try {

			return formatador.parse(ano + mes + dia);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean validarDataNascimento(String dataNascimento) throws ParseException {

		if (dataNascimento == null) {
			return false;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMATO_DATA_BR);
		dateFormat.setLenient(false);

		Date dataNasc = null;
		try {
			dataNasc = dateFormat.parse(dataNascimento);
		} catch (java.text.ParseException e) {

			e.printStackTrace();
			return false;
		}

		Calendar calNascimento = Calendar.getInstance();
		calNascimento.setTime(dataNasc);

		Calendar calNascimento2 = Calendar.getInstance();
		calNascimento2.setTime(dataNasc);

		Calendar calHoje = Calendar.getInstance();

		calNascimento2.add(Calendar.YEAR, IDADE_MAXIMA);

		if (calNascimento2.before(calHoje)) {

			return false;
		}
		// Verificar se a pessoa tem 18 anos ou mais
		calNascimento.add(Calendar.YEAR, IDADE_MINIMA);

		if (calNascimento.before(calHoje)) {
			return true;
		}

		return false;
	}
}
