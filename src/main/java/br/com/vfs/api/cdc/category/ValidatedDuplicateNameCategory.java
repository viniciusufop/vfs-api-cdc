package br.com.vfs.api.cdc.category;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@RequiredArgsConstructor
public class ValidatedDuplicateNameCategory implements Validator {
    private final CategoryRepository categoryRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return NewCategory.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        var newCategory = (NewCategory) target;
        if(categoryRepository.existsByName(newCategory.getName())){
            errors.rejectValue("name", "duplicate-category-name",
                    String.format("Category Name %s existing in database", newCategory.getName()));
        }
    }
}
