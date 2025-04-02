package com.ptit.csdl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ptit.csdl.dto.request.AuthenticationRequest;
import com.ptit.csdl.dto.response.AuthenticationResponse;
import com.ptit.csdl.exception.ApiResponse;
import com.ptit.csdl.service.AuthenticationService;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {
    @Autowired
    AuthenticationService authenticationService;

    @PostMapping(value = "/login")
    public ApiResponse<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        boolean res = authenticationService.isMatches(request);
        return ApiResponse.<AuthenticationResponse>builder().result(
                AuthenticationResponse.builder().isMatches(res).build()).build();
    }
}
