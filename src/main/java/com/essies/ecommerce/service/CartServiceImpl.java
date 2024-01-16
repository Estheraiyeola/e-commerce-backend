package com.essies.ecommerce.service;

import com.essies.ecommerce.data.model.*;
import com.essies.ecommerce.data.repository.CartRepository;
import com.essies.ecommerce.dto.request.AddToCartRequest;
import com.essies.ecommerce.exception.ProductDoesNotExistException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService{
    @Autowired
    private ProductService productService;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private OrderService orderService;
    @Autowired
    @Lazy
    private UserService userService;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private OrderHistoryService orderHistoryService;

    @Autowired
    public  CartServiceImpl(OrderService orderService){
        this.orderService = orderService;
    }
    @Override
    public Cart addToCart(List<AddToCartRequest> addToCartRequestList, Long userId) {
        double total = 0;
        User user = userService.findUserBy(userId);
        List<OrderItem> orderItems = new ArrayList<>();
        Cart cart = new Cart();
        for (AddToCartRequest cartRequest: addToCartRequestList) {
            Product product = productService.findProduct(cartRequest.getProductName());
            if (product != null){
                OrderItem orderItem = new OrderItem();
                orderItem.setProductId(product.getId());
                orderItem.setUnitPrice(product.getPrice());
                orderItem.setQuantity(cartRequest.getQuantity());
                orderItems.add(orderItem);
                long result = orderItem.getUnitPrice().longValue() * orderItem.getQuantity();
                total += result;
            }
            else {
                throw new ProductDoesNotExistException("Product does not exist!");
            }
        }
        cart.setOrderItems(orderItems);
        cart.setTotal(BigDecimal.valueOf(total));
        cart.setDateCreated(LocalDate.now());
        cart.setUserId(userId);
        cartRepository.save(cart);

        Order order = new Order();
        order.setOrderDate(cart.getDateCreated());
        order.setStatus(OrderStatus.COMPLETED_USER);
        order.setBillingAddressId(user.getAddress().getId());
        order.setTotalAmount(cart.getTotal());
        order.setShippingAddressId(user.getAddress().getId());
        order.setPaymentMethod(PaymentMethod.CREDIT_CARD);
        order.setUserId(userId);

        Order savedOrder = orderService.saveOrder(order);
        OrderHistory orderHistory =  orderHistoryService.saveOrderHistory(savedOrder, userId);
        for (OrderItem orderItem : cart.getOrderItems()) {
            orderItem.setOrderId(savedOrder.getId());
            orderItem.setCart(cart);
        }

        // Set the cart's order ID
        cart.setOrderId(savedOrder.getId());

        // Save the cart after associating it with the order items
        Cart savedCart = cartRepository.save(cart);

        // Update the order with the saved cart ID
        savedOrder.setCartId(savedCart.getId());
        savedOrder.setOrderHistory(orderHistory);
        orderService.saveOrder(savedOrder);
        cartRepository.save(savedCart);

        return savedCart;
    }



    @Override
    public void deleteAll() {
        cartRepository.deleteAll();
    }

    @Override
    public List<Cart> removeProductFromCart(String productName) {
        Product product = productService.findProduct(productName);

        for (Cart cart : cartRepository.findAll()) {
            List<OrderItem> orderItemsToRemove = new ArrayList<>();
            System.out.println("Before removal: " + cart.getOrderItems());

            for (OrderItem orderItem : cart.getOrderItems()) {
                if (orderItem.getProductId().equals(product.getId())) {
                    orderItemsToRemove.add(orderItem);
                }
            }

            // Remove the identified order items
            cart.getOrderItems().removeAll(orderItemsToRemove);

            // Call the service to delete the order items from the database
            for (OrderItem orderItem : orderItemsToRemove) {
                orderItemService.deleteByProductId(orderItem.getProductId());
            }
            cartRepository.save(cart);

            System.out.println("After removal: " + cart.getOrderItems());
        }


        return cartRepository.findAll();
    }
}
