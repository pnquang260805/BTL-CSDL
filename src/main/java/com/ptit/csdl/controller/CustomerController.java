package com.ptit.csdl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ptit.csdl.dto.request.CustomerRegisterRequest;
import com.ptit.csdl.dto.request.CustomerUpdateRequest;
import com.ptit.csdl.dto.response.CustomerResponse;
import com.ptit.csdl.exception.ApiResponse;
import com.ptit.csdl.service.CustomerService;

@RestController
@RequestMapping(value = "/api/customers")
public class CustomerController {
    @Autowired 
    private CustomerService customerService;

    @PostMapping
    public ApiResponse<CustomerResponse> register(@RequestBody CustomerRegisterRequest request){
        ApiResponse<CustomerResponse> response = new ApiResponse<CustomerResponse>();
        response.setResult(customerService.register(request));
        return response;
    }

    @GetMapping
    public ApiResponse<List<CustomerResponse>> findUsers(){
        ApiResponse<List<CustomerResponse>> response = new ApiResponse<>();
        response.setResult(customerService.findCustomers());
        return response;
    }

    @PutMapping
    public ApiResponse<CustomerResponse> updateCustomer(@RequestParam Long id, @RequestBody CustomerUpdateRequest request){
        ApiResponse<CustomerResponse> response = new ApiResponse<>();
        response.setResult(customerService.updateCustomer(id, request));
        return response;
    }

    @DeleteMapping
    public ApiResponse<String> deleteCustomer(@RequestParam Long id){
        ApiResponse<String> response = new ApiResponse<>();
        ApiResponse<String> serviceResponse = customerService.deleteCustomer(id);
        response.setResult(serviceResponse.getResult());
        return response;
    }
}
