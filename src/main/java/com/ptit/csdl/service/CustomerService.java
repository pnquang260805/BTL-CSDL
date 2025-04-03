package com.ptit.csdl.service;

import com.ptit.csdl.dto.request.CustomerRegisterRequest;
import com.ptit.csdl.dto.request.CustomerUpdateRequest;
import com.ptit.csdl.dto.response.CustomerResponse;
import com.ptit.csdl.entity.Address;
import com.ptit.csdl.entity.Customer;
import com.ptit.csdl.exception.ApiResponse;
import com.ptit.csdl.exception.AppException;
import com.ptit.csdl.exception.ErrorCode;
import com.ptit.csdl.mapper.CustomerMapper;
import com.ptit.csdl.repository.AddressRepository;
import com.ptit.csdl.repository.CustomerRepository;

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
    AddressRepository addressRepository;


    public CustomerResponse register(CustomerRegisterRequest request){
        Customer customer = customerMapper.toCustomer(request);
        // Xử lý many to many
        Set<Address> addresses = new HashSet<>();
        if(request.getAddressesIds() != null){
            addresses = new HashSet<>(addressRepository.findAllById(request.getAddressesIds()));
        }
        customer.setAddresses(addresses);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        customer.setPassword(passwordEncoder.encode(request.getPassword()));
        CustomerResponse customerResponse = customerMapper.toCustomerResponse(customerRepository.save(customer));
        return customerResponse;
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
