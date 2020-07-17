package br.com.vfs.api.cdc.author;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@RequiredArgsConstructor
public class ValidatedDuplicateEmailAuthor implements Validator {

    private final AuthorRepository authorRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return NewAuthor.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        var newAuthor = (NewAuthor) target;
        if(authorRepository.existsByEmail(newAuthor.getEmail())){
            errors.rejectValue("email", null,
                    String.format("Email author %s existing in database", newAuthor.getEmail()));
        }
    }
}
