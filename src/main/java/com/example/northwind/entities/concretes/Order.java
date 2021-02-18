package com.example.northwind.entities.concretes;

import com.example.northwind.entities.abstracts.IEntity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "orders")
public class Order implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    int id;

    @Column(name = "customer_id")
    private String customerId;
}
