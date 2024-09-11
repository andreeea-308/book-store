package org.bookstore.bookstore.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor

public class OrderEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Orders orders;

    @ManyToOne
    @JoinColumn
    private BookEntity bookEntity;
    private Integer selectedQuantity;
}
