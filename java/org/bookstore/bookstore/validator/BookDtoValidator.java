package org.bookstore.bookstore.validator;

import org.bookstore.bookstore.dto.BookDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Component
public class BookDtoValidator {
    public void validate(BookDto bookDto, BindingResult bindingResult) {
        try {
            Double price = Double.parseDouble(bookDto.getPrice());
            if (price <= 0) {
                FieldError fieldError = new FieldError("bookDto", "price", "Price must be greater than 0.");
                bindingResult.addError(fieldError);
            }
        } catch (NumberFormatException e) {
            FieldError fieldError = new FieldError("bookDto", "price", "Price must be a number.");
            bindingResult.addError(fieldError);
        }
    }
}
