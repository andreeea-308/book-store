package org.bookstore.bookstore.dto;

import lombok.Data;

@Data
public class CartEntryDto {
    private String bookTitle;
    private String pricePerItem;
    private String quantity;
    private String pricePerSelection;
}
