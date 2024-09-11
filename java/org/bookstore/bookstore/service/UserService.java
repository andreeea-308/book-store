package org.bookstore.bookstore.service;


import org.bookstore.bookstore.dto.UserDto;
import org.bookstore.bookstore.entity.UserEntity;
import org.bookstore.bookstore.mapper.UserMapper;
import org.bookstore.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void register(UserDto userDto){
        UserEntity userEntity = userMapper.map(userDto);
        encodePassword(userEntity);
        userRepository.save(userEntity);
    }

    private void encodePassword(UserEntity userEntity) {
        String passwordEncoded = bCryptPasswordEncoder.encode(userEntity.getPassword());
        userEntity.setPassword(passwordEncoded);
    }
}
