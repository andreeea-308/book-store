package org.bookstore.bookstore.repository;


import org.bookstore.bookstore.entity.CartEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartEntryRepository extends JpaRepository<CartEntry, Integer> {
    List<CartEntry> findByCartUserEntityEmail(String userEmail);
}
