package org.bookstore.bookstore.mapper;


import org.bookstore.bookstore.dto.CartEntryDto;
import org.bookstore.bookstore.entity.CartEntry;
import org.springframework.stereotype.Component;

@Component
public class CartEntryMapper {
    public CartEntryDto map(CartEntry cartEntry){
        CartEntryDto cartEntryDto = new CartEntryDto();
        cartEntryDto.setQuantity(String.valueOf(cartEntry.getSelectedQuantity()));
        cartEntryDto.setPricePerItem(String.valueOf(cartEntry.getBookEntity().getPrice()));
        cartEntryDto.setBookTitle(cartEntry.getBookEntity().getTitle());
        cartEntryDto.setPricePerSelection(String.valueOf(cartEntry.getBookEntity().getPrice()
                * cartEntry.getSelectedQuantity()));
        return cartEntryDto;
    }
}
