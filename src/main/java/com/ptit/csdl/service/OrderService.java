package com.ptit.csdl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptit.csdl.dto.request.creation.OrderCreationRequest;
import com.ptit.csdl.dto.response.OrderResponse;
import com.ptit.csdl.entity.Customer;
import com.ptit.csdl.entity.Order;
import com.ptit.csdl.exception.AppException;
import com.ptit.csdl.exception.ErrorCode;
import com.ptit.csdl.mapper.OrderMapper;
import com.ptit.csdl.repository.CustomerRepository;
import com.ptit.csdl.repository.OrderRepository;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private CustomerRepository customerRepository;

    public OrderResponse createOrder(OrderCreationRequest request) {
        Order order = orderMapper.toOrder(request);
        Customer customer = customerRepository.findById(request.getCustomerId()).orElseThrow(
                () -> new AppException(ErrorCode.CUSTOMER_NOT_EXISTS.getMsg(), ErrorCode.CUSTOMER_NOT_EXISTS));
        order.setCustomer(customer);
        // KHÔNG CẦN NHỮNG DÒNG NÀY. NẾU CÓ SẼ GÂY DUPLICATE
        // customer.getOrders().add(order);
        // customerRepository.save(customer);
        return orderMapper.toResponse(orderRepository.save(order));
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public OrderResponse findOrderById(Long id) {
        return orderMapper.toResponse(orderRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_EXISTS.getMsg(), ErrorCode.ORDER_NOT_EXISTS)));
    }

    // Cần UD
}
