package com.example.northwind.business.abstracts;

import java.util.List;

import com.example.northwind.entities.concretes.Order;


public interface IOrderService {
	Order addOrder(Order order);
}
