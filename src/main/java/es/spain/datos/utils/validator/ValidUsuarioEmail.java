package es.spain.datos.utils.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;



@Documented
@Constraint(validatedBy = UsuarioEmailValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidUsuarioEmail {
    String message() default "El e-mail no pertenece al ministerio de Transportes";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
