package org.bookstore.bookstore.mapper;

import org.apache.tomcat.util.codec.binary.Base64;
import org.bookstore.bookstore.dto.BookDto;
import org.bookstore.bookstore.entity.BookEntity;
import org.bookstore.bookstore.enums.GenreType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class BookMapper {

    public BookEntity map(BookDto bookDto, MultipartFile imageFile){
        BookEntity bookEntity = new BookEntity();
        bookEntity.setTitle(bookDto.getTitle());
        bookEntity.setAuthor(bookDto.getAuthor());
        bookEntity.setGenreType(GenreType.valueOf(bookDto.getGenreType()));
        bookEntity.setPublisher(bookDto.getPublisher());
        bookEntity.setNumberOfPages(Integer.valueOf(bookDto.getNumberOfPages()));
        bookEntity.setDescription(bookDto.getDescription());
        bookEntity.setPrice(Double.parseDouble(bookDto.getPrice()));
        bookEntity.setQuantity(Integer.parseInt(bookDto.getQuantity()));
        bookEntity.setImage(convertToByteArray(imageFile));
        return bookEntity;
    }

    public BookDto map(BookEntity bookEntity){
        BookDto bookDto = new BookDto();
        bookDto.setTitle(bookEntity.getTitle());
        bookDto.setAuthor(bookEntity.getAuthor());
        bookDto.setGenreType(String.valueOf(bookEntity.getGenreType()));
        bookDto.setPublisher(bookEntity.getPublisher());
        bookDto.setNumberOfPages(String.valueOf(bookEntity.getNumberOfPages()));
        bookDto.setDescription(bookEntity.getDescription());
        bookDto.setPrice(String.valueOf(bookEntity.getPrice()));
        bookDto.setQuantity(String.valueOf(bookEntity.getQuantity()));
        bookDto.setImage(Base64.encodeBase64String(bookEntity.getImage()));
        return bookDto;
    }

    private byte[] convertToByteArray (MultipartFile multipartFile){
        try {
            return multipartFile.getBytes();
        } catch (IOException e) {
            return new byte[0];
        }
    }
}






