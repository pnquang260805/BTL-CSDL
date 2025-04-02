package com.ptit.csdl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ptit.csdl.dto.request.AuthenticationRequest;
import com.ptit.csdl.exception.ErrorCode;
import com.ptit.csdl.repository.CustomerRepository;
import com.ptit.csdl.exception.AppException;

@Service
public class AuthenticationService {
    @Autowired
    CustomerRepository customerRepository;

    public boolean isMatches(AuthenticationRequest request) {
        var customer = customerRepository.findByCustomerName(request.getUsername()).orElseThrow(
                () -> new AppException(ErrorCode.CUSTOMER_NOT_EXISTS.getMsg(), ErrorCode.CUSTOMER_NOT_EXISTS));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(request.getPassword(), customer.getPassword());
    }
}
