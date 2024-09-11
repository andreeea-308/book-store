package org.bookstore.bookstore.entity;

import jakarta.persistence.*;
import lombok.*;
import org.bookstore.bookstore.enums.GenreType;

import java.util.List;

@Entity(name = "books")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String title;
    @NonNull
    private String author;
    @Enumerated(value = EnumType.STRING)
    private GenreType genreType;
    @NonNull
    private String publisher;
    @NonNull
    private Integer numberOfPages;
    @NonNull
    private String description;
    @NonNull
    private Double price;
    @NonNull
    private Integer quantity;
    @ToString.Exclude
    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] image;
    @OneToMany(mappedBy = "bookEntity")
    private List<CartEntry> cartEntryList;
    @OneToMany(mappedBy = "bookEntity")
    private List<OrderEntry> orderEntryList;
}



