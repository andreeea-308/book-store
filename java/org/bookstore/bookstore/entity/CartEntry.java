package org.bookstore.bookstore.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class CartEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn
    private Cart cart;
    @ManyToOne
    @JoinColumn
    private BookEntity bookEntity;
    private Integer selectedQuantity;
}
