package br.com.vfs.api.cdc.shared.annotations;

import br.com.vfs.api.cdc.shared.validator.ExistElementValidator;
import br.com.vfs.api.cdc.shared.validator.UniqueValueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {ExistElementValidator.class})
@Target({FIELD})
@Retention(RUNTIME)
public @interface ExistElement {

    String message() default "{br.com.vfs.api.cdc.beanvalidation.existElement}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<?> domainClass();
}
