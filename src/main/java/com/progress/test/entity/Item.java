package com.progress.test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code")
    private Integer code;

    @Column(length = 45, name = "description")
    private String description;

    @Column(name = "qty")
    private Integer qty;

    @Column(name = "unit_price")
    private Double unitPrice;

    @Column(name = "barcode", columnDefinition = "LONGBLOB")
    private byte[] barcode;



}
