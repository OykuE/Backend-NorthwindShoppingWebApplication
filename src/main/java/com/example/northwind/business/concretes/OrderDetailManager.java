package com.example.northwind.business.concretes;

import com.example.northwind.business.abstracts.IOrderDetailService;
import com.example.northwind.dataaccess.concretes.OrderDetailRepository;
import com.example.northwind.entities.concretes.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailManager implements IOrderDetailService {

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Override
    public OrderDetail addOrderDetail(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }
}
