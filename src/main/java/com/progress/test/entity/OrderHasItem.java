package com.progress.test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "order_has_item")
public class OrderHasItem {

    @EmbeddedId
    private OrderHasItemId id;

    @Column(name = "unit_price")
    private Double unitPrice;

    @Column(name = "qty")
    private Integer qty;

}
