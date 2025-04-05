package com.ptit.csdl.controller;

import java.util.List;
import java.util.Map;

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

import com.ptit.csdl.dto.request.creation.ProductCreationRequest;
import com.ptit.csdl.dto.response.ProductResponse;
import com.ptit.csdl.entity.Product;
import com.ptit.csdl.exception.ApiResponse;
import com.ptit.csdl.mapper.ProductMapper;
import com.ptit.csdl.service.ProductService;

@RestController
@RequestMapping(value = "/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductMapper productMapper;

    @PostMapping
    public ApiResponse<ProductResponse> createProduct(@RequestBody ProductCreationRequest request){
        ApiResponse<ProductResponse> response = new ApiResponse<>();
        ProductResponse product = productService.createProduct(request);
        response.setResult(product);
        return response;
    }

    @GetMapping
    public ApiResponse<List<ProductResponse>> findAllProducts(){
        List<ProductResponse> products = productService.findAllProducts();
        ApiResponse<List<ProductResponse>> response = new ApiResponse<>();
        response.setResult(products);
        return response;
    }

    @GetMapping(value = "/{productName}")
    public ApiResponse<List<ProductResponse>> findProductsByName(@PathVariable String productName){
        List<ProductResponse> products = productService.findByName(productName);
        ApiResponse<List<ProductResponse>> response = new ApiResponse<>();
        response.setResult(products);
        return response;
    }

    @GetMapping(value = "/id/{id}")
    public ApiResponse<ProductResponse> findProductsById(@PathVariable Long id){
        ProductResponse products = productService.findById(id);
        ApiResponse<ProductResponse> response = new ApiResponse<>();
        response.setResult(products);
        return response;
    }

    @PutMapping
    public ApiResponse<ProductResponse> updateProduct(@RequestParam Map<String, Object> data, @RequestBody ProductCreationRequest request){
        ApiResponse<ProductResponse> response = new ApiResponse<>();
        response.setResult(productService.updateProduct(data, request));
        return response;
    }

    @DeleteMapping(value = "/{id}")
    public ApiResponse<String> deleteProduct(@PathVariable Long id){
        ApiResponse<String> response = new ApiResponse<>();
        productService.deleteProduct(id);
        response.setResult("Done");
        return response;
    }
}
