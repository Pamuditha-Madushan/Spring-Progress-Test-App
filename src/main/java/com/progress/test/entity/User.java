package com.progress.test.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @Column(length = 80, name= "user_id")
    private String userId;

    @Column(unique = true, length = 100, name = "email")
    private String email;

    @Column(length = 45, name = "name")
    private String name;

    @Column(name = "password")
    private String password;



   /* public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    */
}
