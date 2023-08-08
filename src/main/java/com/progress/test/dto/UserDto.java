package com.progress.test.dto;

import lombok.Data;


@Data
public class UserDto {

    private String userId;

    private String email;

    private String name;

    private String password;


   /* public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    */
}
