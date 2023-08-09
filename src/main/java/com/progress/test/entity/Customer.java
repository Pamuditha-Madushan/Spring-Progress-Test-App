package com.progress.test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(generator = "custom-id")
    @GenericGenerator(name = "custom-id", strategy = "com.progress.test.util.CustomIdGenerator")
    @Column(unique = true, length = 80, name = "id")
    private String id;

    @Column(length = 45, name = "name")
    private String name;

    @Column(unique = true, length = 45, name = "address")
    private String address;

    @Column(name = "salary")
    private Double salary;
}
