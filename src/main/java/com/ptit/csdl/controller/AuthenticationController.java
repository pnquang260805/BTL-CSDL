package com.ptit.csdl.controller;

import com.nimbusds.jose.JOSEException;
import com.ptit.csdl.dto.request.creation.IntrospectRequest;
import com.ptit.csdl.dto.response.IntrospectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ptit.csdl.dto.request.AuthenticationRequest;
import com.ptit.csdl.dto.response.AuthenticationResponse;
import com.ptit.csdl.exception.ApiResponse;
import com.ptit.csdl.service.AuthenticationService;

import java.text.ParseException;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {
    @Autowired
    AuthenticationService authenticationService;

    @PostMapping(value = "/login")
    public ApiResponse<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        var res = authenticationService.isMatches(request);
        return ApiResponse.<AuthenticationResponse>builder().result(res).build();

    }

    @PostMapping(value = "/introspect")
    public ApiResponse<IntrospectResponse> introSpec(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        var res = authenticationService.IntroSpectToken(request);
        return ApiResponse.<IntrospectResponse>builder().result(res).build();
    }
}
