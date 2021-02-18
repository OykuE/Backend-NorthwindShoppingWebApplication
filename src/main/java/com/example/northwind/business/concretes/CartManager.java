package com.example.northwind.business.concretes;

import com.example.northwind.business.abstracts.ICartService;
import com.example.northwind.business.abstracts.IOrderDetailService;
import com.example.northwind.business.abstracts.IOrderService;
import com.example.northwind.business.abstracts.IProductService;
import com.example.northwind.dataaccess.concretes.CartRepository;
import com.example.northwind.dataaccess.concretes.ReservedProductRepository;
import com.example.northwind.entities.concretes.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class CartManager implements ICartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IProductService productService;

    @Autowired
    private IOrderDetailService orderDetailService;

    @Override
    public Cart getCartById(Integer id) {
        return cartRepository.findById(id).get();
    }

    @Override
    @Transactional
    public void deleteCartByCustomerId(String customerId) {
        cartRepository.deleteByCustomerId(customerId);
    }

    @Override
    public List<Cart> getAll() {
        return cartRepository.findAll();
    }

    @Override
    public Cart getCartByCustomer(String customerId) {
        if (!cartRepository.existsByCustomerId(customerId)) {
            Cart cart = new Cart();
            cart.setCustomerId(customerId);
            Set<ReservedProduct> reservedProductSet = new HashSet<>();
            cart.setReservedProductSet(reservedProductSet);
            cartRepository.save(cart);
        }
        return cartRepository.findByCustomerId(customerId).get();

    }

    @Override
    public Cart addToCart(Cart cart, Integer productId, Integer quantity) throws Exception {
        if (cart.getReservedProductSet() != null && cart.getReservedProductSet().size() >= 0) {
            for (ReservedProduct reservedProduct : cart.getReservedProductSet()) {
                if (reservedProduct.getProduct().getId() == productId) {
                    reservedProduct.setQuantity(reservedProduct.getQuantity() + quantity);
                    cartRepository.save(cart);
                    return cart;
                }
            }
        }
        Product product = productService.getProductById(productId);
        ReservedProduct reservedProduct = new ReservedProduct();
        reservedProduct.setProduct(product);
        reservedProduct.setCart(cart);
        reservedProduct.setQuantity(quantity);

        cart.getReservedProductSet().add(reservedProduct);
        cartRepository.save(cart);
        return cart;
    }

    @Override
    public void removeFromCart(Cart cart, Integer productId, Integer removeNumber) {
        for (ReservedProduct reservedProduct : cart.getReservedProductSet()) {
            if (reservedProduct.getProduct().getId() == productId) {
                if (reservedProduct.getQuantity() - removeNumber >= 0) {
                    reservedProduct.setQuantity(reservedProduct.getQuantity() - removeNumber);
                } else {
                    cart.getReservedProductSet().remove(reservedProduct);
                }
            }
        }
        cartRepository.save(cart);
    }

    @Override
    public void saleCart(Cart cart){
        Order newOrder = new Order();
        newOrder.setCustomerId(cart.getCustomerId());
        orderService.addOrder(newOrder);

        for (ReservedProduct reservedProduct : cart.getReservedProductSet()) {
            OrderDetail newOrderDetail = new OrderDetail();
            newOrderDetail.setOrderId(newOrder.getId());
            newOrderDetail.setProductId(reservedProduct.getProduct().getId());
            newOrderDetail.setQuantity(reservedProduct.getQuantity());
            newOrderDetail.setUnitPrice(reservedProduct.getProduct().getUnitPrice());
            newOrderDetail.setDiscount(0);
            orderDetailService.addOrderDetail(newOrderDetail);

            cartRepository.delete(cart);
        }

    }

}
