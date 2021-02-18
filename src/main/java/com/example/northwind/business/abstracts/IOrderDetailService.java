package com.example.northwind.business.abstracts;

import com.example.northwind.entities.concretes.Cart;
import com.example.northwind.entities.concretes.OrderDetail;

public interface IOrderDetailService {
    OrderDetail addOrderDetail(OrderDetail orderDetail);
}
