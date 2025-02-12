package com.pruebatecnica.nttdata.clientes_app.validation;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class TipoDocumentoValidatorTest {

    private TipoDocumentoValidator validator = new TipoDocumentoValidator();
    private ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);

    @Test
    void isValid_TiposValidos_RetornaTrue() {
        assertTrue(validator.isValid("C", context));
        assertTrue(validator.isValid("P", context));
        assertTrue(validator.isValid("c", context));
        assertTrue(validator.isValid("p", context));
    }

    @Test
    void isValid_TiposInvalidos_RetornaFalse() {
        assertFalse(validator.isValid("A", context));
        assertFalse(validator.isValid(null, context));
        assertFalse(validator.isValid("123", context));
    }
}