package com.pruebatecnica.nttdata.clientes_app.validation;

import com.pruebatecnica.nttdata.clientes_app.entity.TipoDocumento;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.EnumUtils;

public class TipoDocumentoValidator implements ConstraintValidator<ValidTipoDocumento, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return false;
        }
        return EnumUtils.isValidEnum(TipoDocumento.class, value.toUpperCase());
    }
}

