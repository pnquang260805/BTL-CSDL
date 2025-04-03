package com.ptit.csdl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ptit.csdl.dto.request.AddressCreationRequest;
import com.ptit.csdl.dto.response.AddressResponse;
import com.ptit.csdl.exception.ApiResponse;
import com.ptit.csdl.service.AddressService;

@RestController
@RequestMapping(value = "/api/addresses")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @PostMapping
    public ApiResponse<AddressResponse> createAddress(@RequestBody AddressCreationRequest request){
        ApiResponse<AddressResponse> response = new ApiResponse<>();
        response.setResult(addressService.createAddress(request));
        return response;
    }

    @GetMapping
    public ApiResponse<List<AddressResponse>> getAddresses(){
        ApiResponse<List<AddressResponse>> response = new ApiResponse<>();
        response.setResult(addressService.getAddresses());
        return response;
    }
}
