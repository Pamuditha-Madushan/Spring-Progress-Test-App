package com.progress.test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@Data
public class User {

    @Id
    @Column(length = 80, name= "user_id")
    private String id;

    @Column(unique = true, length = 100, name = "email")
    private String email;

    @Column(length = 45, name = "name")
    private String name;

    @Column(name = "password")
    private String password;



}
