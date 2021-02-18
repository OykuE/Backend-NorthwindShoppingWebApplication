package com.example.northwind.business.abstracts;

import com.example.northwind.entities.concretes.Cart;
import com.example.northwind.entities.concretes.Product;

import java.util.List;


public interface ICartService {
    Cart getCartById(Integer id);

    void deleteCartByCustomerId(String customerId);

    List<Cart> getAll();

    Cart getCartByCustomer(String customerId);

    Cart addToCart(Cart cart, Integer productId, Integer quantity) throws Exception;

    void removeFromCart(Cart cart, Integer productId, Integer removeNumber);

    void saleCart(Cart cart) throws Exception;

}
