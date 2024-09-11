package org.bookstore.bookstore.dto;

import lombok.Data;

@Data
public class UserDto {
    private String fullName;
    private String email;
    private String dateOfBirth;
    private String password;
    private String role;
}
