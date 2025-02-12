package com.pruebatecnica.nttdata.clientes_app.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TipoDocumentoValidator.class)
@Documented
public @interface ValidTipoDocumento {
    String message() default "Tipo de documento inválido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}