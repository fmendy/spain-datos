package es.spain.datos.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;

import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import es.spain.datos.dto.ComboDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Utils {

	public static String dateToMesAnnoString(Date date) {

		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Madrid"));
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);

		// Obtienes el mes actual
		Month mes = Month.of(month);
		// Obtienes el nombre del mes
		String nombreMes = mes.getDisplayName(TextStyle.FULL, Locale.forLanguageTag("es-ES"));
		return nombreMes + " " + year;
	}

	public static String dateToAnno(Date date) {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Madrid"));
		cal.setTime(date);

		return Integer.toString(cal.get(Calendar.YEAR));
	}

	public static String getMessage(String key) {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("messages", Locale.getDefault());
		return resourceBundle.getString(key);
	}

	public static String getParameterSort(Sort sort) {
		if (sort != null && StringUtils.hasText(sort.toString()) && !sort.toString().equalsIgnoreCase("UNSORTED")) {
			String[] aux = sort.toString().split(" ");
			return "&sort=" + aux[0].trim().replace(":", "") + "," + aux[1].trim().replace(":", "");
		}
		return "";
	}

	public static Date dateSetHoraMinutoSegundoMilisegundo(Date date, int hora, int min, int sec, int milisec) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, hora);
		cal.set(Calendar.MINUTE, min);
		cal.set(Calendar.SECOND, sec);
		cal.set(Calendar.MILLISECOND, milisec);
		return cal.getTime();
	}

	public static String dateToString(Date date, String pattern) {
		DateFormat df = new SimpleDateFormat(pattern);
		return df.format(date);
	}

	public static Long booleanToLong(boolean boo) {
		return boo ? 1L : 0L;
	}

	public static boolean longToBoolean(Long l) {
		return (l != null && l == 1L);
	}

	public static String booleanToSiNo(boolean boo) {
		return (boo ? getMessage("global.si") : getMessage("global.no"));
	}

	public static String longToSiNo(Long lo) {
		return booleanToSiNo(longToBoolean(lo));
	}

	public static Long intToLong(int i) {
		return Long.parseLong(Integer.toString(i));
	}
	
	public static void orderComboForm(final List<ComboDTO> list) {
		list.sort(Comparator.comparing(ComboDTO::getValue));
	}

}
