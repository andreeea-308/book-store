package org.bookstore.bookstore.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bookstore.bookstore.enums.OrderStatus;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<OrderEntry> orderEntryList;

    @ManyToOne
    @JoinColumn
    private UserEntity userEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Address address;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}
