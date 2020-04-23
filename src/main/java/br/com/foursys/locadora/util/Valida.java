package br.com.foursys.locadora.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.caelum.stella.ValidationMessage;
import br.com.caelum.stella.validation.CPFValidator;

public class Valida {
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);

	public static boolean verificaVazio(String aux) {
		return aux.trim().equals("") || aux == null;
	}

	public static boolean validarCpf(String cpf) {
		CPFValidator cpfValidator = new CPFValidator();
		List<ValidationMessage> erros = cpfValidator.invalidMessagesFor(cpf);
		return erros.size() <= 0;
	}

	public static boolean validarRg(String rg) {
		if (rgNumerosRepetidos(rg)) {
			return false;
		}
		String a, b, c, d, e, f, g, h, i;
		int resul, auxDigito;
		String caracteresRg = rg.replace(".", "").replace("-", "");
		a = caracteresRg.charAt(0) + "";
		b = caracteresRg.charAt(1) + "";
		c = caracteresRg.charAt(2) + "";
		d = caracteresRg.charAt(3) + "";
		e = caracteresRg.charAt(4) + "";
		f = caracteresRg.charAt(5) + "";
		g = caracteresRg.charAt(6) + "";
		h = caracteresRg.charAt(7) + "";
		i = caracteresRg.charAt(8) + "";
		if (i.equals("x") || i.equals("X")) {
			auxDigito = 10;
		} else {
			auxDigito = Integer.parseInt(i);
		}
		resul = (9 * Integer.parseInt(a)) + (8 * Integer.parseInt(b)) + (7 * Integer.parseInt(c))
				+ (6 * Integer.parseInt(d)) + (5 * Integer.parseInt(e)) + (4 * Integer.parseInt(f))
				+ (3 * Integer.parseInt(g)) + (2 * Integer.parseInt(h));
		resul = resul % 11;
		return resul == auxDigito;
	}
	
	private static boolean rgNumerosRepetidos(String rg) {
		return (rg.equals("00.000.000-0") || rg.equals("11.111.111-1") || rg.equals("22.222.222-2")
				|| rg.equals("33.333.333-3") || rg.equals("44.444.444-4") || rg.equals("55.555.555-5")
				|| rg.equals("66.666.666-6") || rg.equals("77.777.777-7") || rg.equals("88.888.888-8")
				|| rg.equals("99.999.999-9"));
	}

	public static boolean isNumeric(String numero) {
		try {
			Integer.parseInt(numero);
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}

	public static boolean validarEmail(String email) {
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public static boolean validarData(String data) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			sdf.setLenient(false);
			sdf.parse(data);
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate dataVerificada = LocalDate.parse(data, dtf);
			LocalDate hoje = LocalDate.now();
			return dataVerificada.compareTo(hoje) <= 0;
		} catch (ParseException ex) {
			return false;
		}
	}

	public static boolean dataMenorQueHoje(Date data) {
		Date dataHoje = new Date(System.currentTimeMillis());
		return data.before(dataHoje);
	}

	public static boolean validaComboVazia(ArrayList<String> lista) {
		return lista.isEmpty();
	}
}
