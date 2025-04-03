package com.ptit.csdl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ptit.csdl.dto.request.creation.OrderCreationRequest;
import com.ptit.csdl.dto.response.OrderResponse;
import com.ptit.csdl.exception.ApiResponse;
import com.ptit.csdl.mapper.OrderMapper;
import com.ptit.csdl.service.OrderService;

@RestController
@RequestMapping(value = "/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderMapper orderMapper;

    @PostMapping
    public ApiResponse<OrderResponse> createOrder(@RequestBody OrderCreationRequest request){
        ApiResponse<OrderResponse> response = new ApiResponse<>();
        response.setResult(orderService.createOrder(request));
        return response;
    }

    @GetMapping
    public ApiResponse<List<OrderResponse>> getOrders(){
        ApiResponse<List<OrderResponse>> response = new ApiResponse<>();
        response.setResult(orderMapper.toResponse(orderService.getOrders()));
        return response;
    }

    @GetMapping(value = "/{id}")
    public ApiResponse<OrderResponse> getOrderById(@PathVariable Long id){
        ApiResponse<OrderResponse> response = new ApiResponse<OrderResponse>();
        response.setResult(orderService.findOrderById(id));
        return response;
    }
}
