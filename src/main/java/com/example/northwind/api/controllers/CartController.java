package com.example.northwind.api.controllers;

import com.example.northwind.api.dto.ProductDto;
import com.example.northwind.business.abstracts.ICartService;
import com.example.northwind.business.abstracts.IOrderDetailService;
import com.example.northwind.business.abstracts.IOrderService;
import com.example.northwind.business.abstracts.IProductService;
import com.example.northwind.entities.concretes.Cart;
import com.example.northwind.entities.concretes.Order;
import com.example.northwind.entities.concretes.OrderDetail;
import com.example.northwind.entities.concretes.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

    @Autowired
    private ICartService cartService;

    @GetMapping("/carts")
    public List<Cart> getAll() {
        return cartService.getAll();
    }

    @GetMapping("/{customer-id}")
    public Cart getCartContent(@PathVariable(value = "customer-id") String customerId) {
        return cartService.getCartByCustomer(customerId);
    }

    @PutMapping("/{customer-id}/new-product")
    public ResponseEntity<?> addNewProduct(@PathVariable(value = "customer-id") String customerId, @RequestBody ProductDto productDto) throws Exception {

        Cart cart = cartService.getCartByCustomer(customerId);
        cartService.addToCart(cart, productDto.getProductId(), productDto.getQuantity());

        return ResponseEntity.ok("Product is added to cart!");
    }

    @PutMapping("/{customer-id}/cancel-product")
    public ResponseEntity<?> cancelProduct(@PathVariable(value = "customer-id") String customerId, @RequestBody ProductDto productDto) throws Exception {

        Cart cart = cartService.getCartByCustomer(customerId);
        cartService.removeFromCart(cart, productDto.getProductId(), productDto.getQuantity());

        return ResponseEntity.ok("Cart is updated!");
    }

    @DeleteMapping("/{customer-id}")
    public ResponseEntity<?> deleteCart(@PathVariable(value = "customer-id") String customerId) {
        cartService.deleteCartByCustomerId(customerId);
        return ResponseEntity.ok("Cart is removed");
    }

    @PostMapping("/{customer-id}/makeSale")
    public ResponseEntity<?> makeSale(@PathVariable(value = "customer-id") String customerId) throws Exception { //TODO move products to order and order details
        Cart cart = cartService.getCartByCustomer(customerId);
        cartService.saleCart(cart);
        return ResponseEntity.ok("Order is successfully created.");
    }

}
