package org.bookstore.bookstore.dto;

import lombok.Data;

import java.util.List;

@Data
public class CartDto {
    private List<CartEntryDto> cartEntryDtoList;
    private String totalPrice;
}
