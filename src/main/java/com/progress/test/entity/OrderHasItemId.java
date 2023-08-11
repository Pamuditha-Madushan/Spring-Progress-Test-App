package com.progress.test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class OrderHasItemId implements Serializable {


    @ManyToOne
    @JoinColumn(name = "order_order_id", referencedColumnName = "order_Id", columnDefinition = "VARCHAR(80)")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "item_code", referencedColumnName = "code")
    private Item item;
}
