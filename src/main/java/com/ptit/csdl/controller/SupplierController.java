package com.ptit.csdl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ptit.csdl.dto.request.creation.SupplierCreationRequest;
import com.ptit.csdl.dto.request.update.SupplierUpdateRequest;
import com.ptit.csdl.dto.response.SupplierResponse;
import com.ptit.csdl.entity.Supplier;
import com.ptit.csdl.exception.ApiResponse;
import com.ptit.csdl.service.SupplierService;

@RestController
@RequestMapping(value = "/api/suppliers")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @PostMapping
    public ApiResponse<SupplierResponse> createSupplier(@RequestBody SupplierCreationRequest request){
        SupplierResponse supplier = supplierService.createSupplier(request);
        return ApiResponse.<SupplierResponse>builder().result(supplier).build();
    }

    @GetMapping
    public ApiResponse<List<SupplierResponse>> getAllSuppliers(){
        List<SupplierResponse> supplierResponses = supplierService.getAllSuppliers();
        ApiResponse<List<SupplierResponse>> response = new ApiResponse<>();
        response.setResult(supplierResponses);
        return response;
    }

    @GetMapping(params = "id")
    public ApiResponse<SupplierResponse> getById(@RequestParam Long id){
        SupplierResponse supplierResponse = supplierService.getSupplierById(id);
        return ApiResponse.<SupplierResponse>builder().result(supplierResponse).build();
    }

    @PutMapping(params = "id")
    public ApiResponse<SupplierResponse> updateSupplier(@RequestParam Long id, @RequestBody SupplierUpdateRequest request){
        return ApiResponse.<SupplierResponse>builder().result(supplierService.updateSupplier(id, request)).build();
    }

    @DeleteMapping(params = "id")
    public ApiResponse<List<SupplierResponse>> deleteSupplier(@RequestParam Long id){
        List<SupplierResponse> supplierResponses = supplierService.deleteSupplier(id);
        ApiResponse<List<SupplierResponse>> response = new ApiResponse<>();
        response.setResult(supplierResponses);
        return response;
    }
}
