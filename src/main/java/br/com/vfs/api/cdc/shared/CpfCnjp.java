package br.com.vfs.api.cdc.shared;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {CpfCnpjValidator.class})
@Target({FIELD})
@Retention(RUNTIME)
public @interface CpfCnjp {

    String message() default "{br.com.vfs.api.cdc.beanvalidation.cpfcnpj}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
