package com.progress.test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "`order`")
public class Order {

    @Id
    @GeneratedValue(generator = "custom-id")
    @GenericGenerator(name = "custom-id", strategy = "com.progress.test.util.CustomIdGenerator")
    @Column(length = 80, name = "order_id")
    private String orderId;

    @Column(name = "order_Date")
    private Date orderDate;

    @Column(name = "cost")
    private Double cost;


   @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id", columnDefinition = "VARCHAR(80)")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", columnDefinition = "VARCHAR(80)")
    private User user ;



}
