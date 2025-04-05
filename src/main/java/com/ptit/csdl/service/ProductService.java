package com.ptit.csdl.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptit.csdl.dto.request.creation.ProductCreationRequest;
import com.ptit.csdl.dto.response.ProductResponse;
import com.ptit.csdl.entity.Category;
import com.ptit.csdl.entity.Product;
import com.ptit.csdl.mapper.ProductMapper;
import com.ptit.csdl.repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired ProductMapper productMapper;

    public ProductResponse createProduct(ProductCreationRequest request){
        // Category category = categoryRepository.findById(request.getCategoryIds());
        Product product = productMapper.toProduct(request);
        return productMapper.toResponse(productRepository.save(product));
    }

    public List<ProductResponse> findAllProducts(){
        List<Product> products = productRepository.findAll();
        return productMapper.toResponse(products);
    }

    public ProductResponse findById(Long id){
        ProductResponse product = productMapper.toResponse(productRepository.findById(id).orElseThrow());
        return product;
    }

    public List<ProductResponse> findByName(String productName){
        List<Product> products = productRepository.findByProductNameContains(productName);
        return productMapper.toResponse(products);
    }

    public ProductResponse updateProduct(Map<String, Object> data, ProductCreationRequest request){
        String idStr = String.valueOf(data.get("id"));
        Long id = Long.parseLong(idStr);
        Product product = productRepository.findById(id).orElseThrow();
        productMapper.updateProduct(product, request);
        return productMapper.toResponse(productRepository.save(product));
    }

    public void deleteProduct(Long id){
        Product product = productRepository.findById(id).orElseThrow(null);
        productRepository.delete(product);
    }
}
