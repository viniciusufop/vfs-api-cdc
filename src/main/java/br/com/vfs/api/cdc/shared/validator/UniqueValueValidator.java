package br.com.vfs.api.cdc.shared.validator;

import br.com.vfs.api.cdc.shared.annotations.UniqueValue;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {
    private static final String QUERY = "select 1 from %s where %s=:value";
    private String fieldName;
    private Class<?> clazz;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(final UniqueValue constraintAnnotation) {
        fieldName = constraintAnnotation.fieldName();
        clazz = constraintAnnotation.domainClass();
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        var query = entityManager.createQuery(String.format(QUERY, clazz.getName(), fieldName));
        query.setParameter("value", value);
        var resultList = query.getResultList();
        Assert.isTrue(resultList.size() <= 1, String.format("Duplicate value (%s) in attribute %s and class %s", value, fieldName, clazz.getSimpleName()));
        return resultList.isEmpty();
    }
}
