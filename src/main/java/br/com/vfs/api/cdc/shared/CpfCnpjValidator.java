package br.com.vfs.api.cdc.shared;

import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class CpfCnpjValidator implements ConstraintValidator<CpfCnjp, String> {

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        if(Objects.isNull(value)) return true;
        final var cpfValidator = new CPFValidator();
        final var cnpjValidator = new CNPJValidator();
        cpfValidator.initialize(null);
        cnpjValidator.initialize(null);
        return cpfValidator.isValid(value, context) || cnpjValidator.isValid(value, context);
    }
}
