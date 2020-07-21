package br.com.vfs.api.cdc.purchase;

import br.com.vfs.api.cdc.book.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

@RequiredArgsConstructor
public class TotalNewCartValidator implements Validator {

    private final BookRepository bookRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return NewPurchase.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        final var newPurchase = (NewPurchase) target;
        final var newCart = newPurchase.getNewCart();
        if(Objects.nonNull(newCart)){
            final var totalCalculate = newCart.totalCalculate(bookRepository);
            if(totalCalculate.compareTo(newCart.getTotal()) != 0)
                errors.rejectValue("newCart", null, String.format("Invalid Total Value %s, Total calculate is %s", newCart.getTotal(), totalCalculate));
        }
    }
}
