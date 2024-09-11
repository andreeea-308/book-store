package org.bookstore.bookstore.mapper;


import org.bookstore.bookstore.dto.UserDto;
import org.bookstore.bookstore.entity.UserEntity;
import org.bookstore.bookstore.enums.UserRole;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UserMapper {
    public UserEntity map(UserDto userDto){
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userDto.getFullName());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPassword(userDto.getPassword());
        userEntity.setDateOfBirth(LocalDate.parse(userDto.getDateOfBirth()));
        userEntity.setRole(UserRole.valueOf(userDto.getRole()));
//        Cart cart = new Cart();
//        userEntity.setCart(cart);
//        cart.setUserEntity(userEntity);
        return userEntity;
    }
}
