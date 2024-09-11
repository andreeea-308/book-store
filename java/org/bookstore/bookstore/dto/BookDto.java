package org.bookstore.bookstore.dto;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;
import org.bookstore.bookstore.enums.GenreType;

@Data
public class BookDto {

    private String title;
    private String author;
    private String genreType;
    private String publisher;
    private String numberOfPages;
    private String description;
    private String price;
    private String quantity;
    private String id;

    @ToString.Exclude
    private String image;
}



