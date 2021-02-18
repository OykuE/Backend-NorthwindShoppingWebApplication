package com.example.northwind.entities.concretes;

import com.example.northwind.entities.abstracts.IEntity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@IdClass(OrderDetailsId.class)
@Table(name = "order_details")
public class OrderDetail implements IEntity {

    @Id
    @Column(name = "order_id")
    int orderId;

    @Id
    @Column(name = "product_id")
    private int productId;

    @Column(name = "unit_price")
    private double unitPrice;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "discount")
    private Integer discount;

}
