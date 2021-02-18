package com.example.northwind.business.concretes;

import com.example.northwind.business.abstracts.IOrderService;
import com.example.northwind.dataaccess.concretes.OrderRepository;
import com.example.northwind.entities.concretes.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderManager implements IOrderService{

	@Autowired
	OrderRepository orderRepository;

	@Override
	public Order addOrder(Order order) {
		return orderRepository.save(order);
	}

}
