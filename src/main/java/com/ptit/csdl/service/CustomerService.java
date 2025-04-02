package com.ptit.csdl.service;

import com.ptit.csdl.dto.request.CustomerRegisterRequest;
import com.ptit.csdl.dto.request.CustomerUpdateRequest;
import com.ptit.csdl.dto.response.CustomerResponse;
import com.ptit.csdl.entity.Customer;
import com.ptit.csdl.exception.ApiResponse;
import com.ptit.csdl.exception.AppException;
import com.ptit.csdl.exception.ErrorCode;
import com.ptit.csdl.mapper.CustomerMapper;
import com.ptit.csdl.repository.CustomerRepository;

import java.util.List;

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

    public CustomerResponse register(CustomerRegisterRequest request){
        Customer customer = customerMapper.toCustomer(request);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        customer.setPassword(passwordEncoder.encode(request.getPassword()));
        return customerMapper.toCustomerResponse(customerRepository.save(customer));
    }

    public List<CustomerResponse> findCustomers(){
        return customerMapper.toCustomerResponse(customerRepository.findAll());
    }

    public CustomerResponse updateCustomer(Long id, CustomerUpdateRequest request){
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.CUSTOMER_NOT_EXISTS.getMsg(), ErrorCode.CUSTOMER_NOT_EXISTS));
        customerMapper.updateCustomer(customer, request);
        return customerMapper.toCustomerResponse(customerRepository.save(customer));
    }

    public ApiResponse<String> deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.CUSTOMER_NOT_EXISTS.getMsg(), ErrorCode.CUSTOMER_NOT_EXISTS));
        customerRepository.delete(customer);
        ApiResponse<String> response = new ApiResponse<>();
        response.setResult("Deleted customer " + String.valueOf(id));
        return response;
    }
}
