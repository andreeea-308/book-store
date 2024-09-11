package org.bookstore.bookstore.service;

import org.bookstore.bookstore.dto.BookDto;
import org.bookstore.bookstore.entity.BookEntity;
import org.bookstore.bookstore.mapper.BookMapper;
import org.bookstore.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookMapper bookMapper;

    public void addBook(BookDto bookDto, MultipartFile imageFile){
        BookEntity bookEntity = bookMapper.map(bookDto, imageFile);
        bookRepository.save(bookEntity);
    }

    public List<BookDto> getAllBookDtoList() {
        List<BookEntity> bookEntityList = bookRepository.findAll();
        return bookEntityList.stream()
                .map(bookMapper::map)
                .toList();
    }

    public Optional<BookDto> getBookDtoByBookId(String bookId){
        Optional<BookEntity> optionalBookEntity = bookRepository.findById(Integer.parseInt(bookId));
        if (optionalBookEntity.isEmpty()){
            return Optional.empty();
        }
        BookEntity bookEntity = optionalBookEntity.get();
        BookDto bookDto = bookMapper.map(bookEntity);
        return Optional.of(bookDto);
    }

    public List<BookDto> searchBookDtoByBookTitle(String bookTitle) {
        List<BookEntity> bookEntityList = bookRepository.findByTitleContaining(bookTitle);
        return bookEntityList.stream()
                .map(bookMapper::map)
                .toList();
    }
}

