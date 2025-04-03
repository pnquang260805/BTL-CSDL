package com.ptit.csdl.service;

import com.ptit.csdl.dto.request.creation.CustomerRegisterRequest;
import com.ptit.csdl.dto.request.update.CustomerUpdateRequest;
import com.ptit.csdl.dto.response.CustomerResponse;
import com.ptit.csdl.entity.Customer;
import com.ptit.csdl.entity.Order;
import com.ptit.csdl.exception.ApiResponse;
import com.ptit.csdl.exception.AppException;
import com.ptit.csdl.exception.ErrorCode;
import com.ptit.csdl.mapper.CustomerMapper;
import com.ptit.csdl.repository.CustomerRepository;
import com.ptit.csdl.repository.OrderRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    CustomerMapper customerMapper;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    OrderRepository orderRepository;

    public CustomerResponse register(CustomerRegisterRequest request) {
        Customer customer = customerMapper.toCustomer(request);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        customer.setPassword(passwordEncoder.encode(request.getPassword()));
        CustomerResponse customerResponse = customerMapper.toCustomerResponse(customerRepository.save(customer));
        return customerResponse;
    }

    public List<CustomerResponse> findCustomers() {
        return customerMapper.toCustomerResponse(customerRepository.findAll());
    }

    public CustomerResponse findCustomerById(Long id) {
        return customerMapper.toCustomerResponse(customerRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.CUSTOMER_NOT_EXISTS.getMsg(), ErrorCode.CUSTOMER_NOT_EXISTS)));
    }

    public CustomerResponse updateCustomer(Long id, CustomerUpdateRequest request) {
        Customer customer = customerRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.CUSTOMER_NOT_EXISTS.getMsg(), ErrorCode.CUSTOMER_NOT_EXISTS));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        customerMapper.updateCustomer(customer, request);
        customer.setPassword(passwordEncoder.encode(request.getPassword()));
        return customerMapper.toCustomerResponse(customerRepository.save(customer));
    }

    public ApiResponse<String> deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.CUSTOMER_NOT_EXISTS.getMsg(), ErrorCode.CUSTOMER_NOT_EXISTS));
        customerRepository.delete(customer);
        ApiResponse<String> response = new ApiResponse<>();
        response.setResult("Deleted customer " + String.valueOf(id));
        return response;
    }
}
