package org.bookstore.bookstore.service;


import org.bookstore.bookstore.dto.CartDto;
import org.bookstore.bookstore.dto.CartEntryDto;
import org.bookstore.bookstore.dto.SelectionDto;
import org.bookstore.bookstore.entity.BookEntity;
import org.bookstore.bookstore.entity.Cart;
import org.bookstore.bookstore.entity.CartEntry;
import org.bookstore.bookstore.mapper.CartEntryMapper;
import org.bookstore.bookstore.repository.BookRepository;
import org.bookstore.bookstore.repository.CartEntryRepository;
import org.bookstore.bookstore.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CartEntryRepository cartEntryRepository;

    @Autowired
    private CartEntryMapper cartEntryMapper;

    public void addToCart(String loggedInUserEmail, SelectionDto selectionDto, String bookId){
        Cart cart = cartRepository.findByUserEntityEmail(loggedInUserEmail);
        Optional<BookEntity> optionalBookEntity = bookRepository.findById(Integer.valueOf(bookId));
        if(optionalBookEntity.isEmpty()){
            throw new RuntimeException("Book does not exist!!!");
        }
        BookEntity bookEntity = optionalBookEntity.get();
        CartEntry cartEntry = createCartEntry(selectionDto, cart, bookEntity);
        cartEntryRepository.save(cartEntry);
    }

    private CartEntry createCartEntry(SelectionDto selectionDto, Cart cart, BookEntity bookEntity) {
        Integer selectedQuantity = Integer.valueOf(selectionDto.getQuantity());

        CartEntry cartEntry = new CartEntry();
        cartEntry.setCart(cart);
        cartEntry.setBookEntity(bookEntity);
        cartEntry.setSelectedQuantity(selectedQuantity);
        return cartEntry;
    }

    public CartDto getCartDtoFor(String authenticatedUserEmail) {
        List<CartEntry> cartEntryList = cartEntryRepository.findByCartUserEntityEmail(authenticatedUserEmail);
        List<CartEntryDto> cartEntryDtoList = createCartEntryDtoList(cartEntryList);
        Double totalPrice = calculateTotalPrice(cartEntryList);


        CartDto cartDto = new CartDto();
        cartDto.setCartEntryDtoList(cartEntryDtoList);
        cartDto.setTotalPrice(String.valueOf(totalPrice));
        return cartDto;
    }

    private Double calculateTotalPrice(List<CartEntry> cartEntryList) {
        Double totalPrice = 0.0;
        for(CartEntry cartEntry: cartEntryList){
            double cartEntryPrice = cartEntry.getSelectedQuantity() * cartEntry.getBookEntity().getPrice();
            totalPrice += cartEntryPrice;
        }
        return totalPrice;
    }

    private List<CartEntryDto> createCartEntryDtoList(List<CartEntry> cartEntryList) {
        List<CartEntryDto> cartEntryDtoList = cartEntryList.stream()
                .map(cartEntry -> cartEntryMapper.map(cartEntry))
                .toList();
        return cartEntryDtoList;
    }
}
