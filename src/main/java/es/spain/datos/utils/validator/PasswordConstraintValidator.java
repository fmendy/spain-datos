package es.spain.datos.utils.validator;

import jakarta.validation.ConstraintValidator; // Para Spring Boot 3+
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    // Regex: Al menos 8 caracteres, al menos una mayúscula, una minúscula, un número y un carácter especial
    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{8,}$";

    private Pattern pattern;
    private Matcher matcher;

    @Override
    public void initialize(ValidPassword constraintAnnotation) {
        pattern = Pattern.compile(PASSWORD_PATTERN);
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) {
            return false; // O true si permites contraseñas nulas, aunque @NotEmpty ya lo cubre
        }
        matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                            "{usuario.form.password.invalid.complejidad}") // Mensaje más específico
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}