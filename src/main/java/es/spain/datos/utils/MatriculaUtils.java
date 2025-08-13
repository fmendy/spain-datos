package es.spain.datos.utils;

import org.springframework.util.StringUtils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MatriculaUtils {

	public boolean validar(String matricula) {
		if (!StringUtils.hasText(matricula) || matricula.length() != 7) {
			return false;
		}
		String numeros = matricula.substring(0, 4);
		String letras = matricula.substring(4, 7);
		return numeros.matches("\\d{4}") && letras.matches("[BCDFGHJKLMNPRSTVWXYZ]{3}");
	}
}
