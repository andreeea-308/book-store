package org.bookstore.bookstore.service;


import org.bookstore.bookstore.dto.OrderDto;
import org.bookstore.bookstore.entity.*;
import org.bookstore.bookstore.enums.OrderStatus;
import org.bookstore.bookstore.repository.CartEntryRepository;
import org.bookstore.bookstore.repository.OrderRepository;
import org.bookstore.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private CartEntryRepository cartEntryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    public void finalizeOrder(OrderDto orderDto, String authenticatedUserEmail){
         List<CartEntry> cartEntryList = cartEntryRepository.findByCartUserEntityEmail(authenticatedUserEmail);
         Orders orders = createOrderFrom(cartEntryList);
         Address address = createAdressFrom(orderDto);
         orders.setAddress(address);
         Optional<UserEntity> optionalUser = userRepository.findByEmail(authenticatedUserEmail);
         if (optionalUser.isEmpty()){
             throw new RuntimeException("Authenticated user is not in the database.");
         }
         orders.setUserEntity(optionalUser.get());
         orderRepository.save(orders);
    }

    private Address createAdressFrom(OrderDto orderDto) {
        Address address = new Address();
        address.setCity(orderDto.getCity());
        address.setFlatAndFloor(orderDto.getFlatAndFloor());
        address.setStreetAndNumber(orderDto.getStreetAndNumber());
        address.setPhoneNumber(orderDto.getPhoneNumber());
        return address;
    }

    private Orders createOrderFrom(List<CartEntry> cartEntryList) {
        Orders orders = new Orders();
        List<OrderEntry> orderEntryList = new ArrayList<>();
        for (CartEntry cartEntry : cartEntryList){
            OrderEntry orderEntry = new OrderEntry();
            orderEntry.setBookEntity(cartEntry.getBookEntity());
            orderEntry.setSelectedQuantity(cartEntry.getSelectedQuantity());
            orderEntry.setOrders(orders);
            orderEntryList.add(orderEntry);
        }
        orders.setOrderEntryList(orderEntryList);
        orders.setOrderStatus(OrderStatus.NEW);
        return orders;
    }
}
