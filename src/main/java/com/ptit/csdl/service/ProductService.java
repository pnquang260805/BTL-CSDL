package com.ptit.csdl.service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptit.csdl.dto.request.creation.ProductCreationRequest;
import com.ptit.csdl.dto.response.ProductResponse;
import com.ptit.csdl.entity.Category;
import com.ptit.csdl.entity.Product;
import com.ptit.csdl.entity.Supplier;
import com.ptit.csdl.exception.AppException;
import com.ptit.csdl.exception.ErrorCode;
import com.ptit.csdl.mapper.ProductMapper;
import com.ptit.csdl.repository.CategoryRepository;
import com.ptit.csdl.repository.ProductRepository;
import com.ptit.csdl.repository.SupplierRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SupplierRepository supplierRepository;

    public ProductResponse createProduct(ProductCreationRequest request) {
        // Category category = categoryRepository.findById(request.getCategoryIds());
        Product product = productMapper.toProduct(request);
        Set<Long> categoryIds = request.getCategoryIds();
        for (Long id : categoryIds) {
            Category category = categoryRepository.findById(id).orElseThrow(
                    () -> new AppException(ErrorCode.CATEGORY_NOT_FOUND.getMsg(), ErrorCode.CATEGORY_NOT_FOUND));
            product.getCategories().add(category); 
            // service nào thì add entity khác vào: ví dụ: product service -> add entity category
        }

        Long supplierId = request.getSupplierId();
        Supplier supplier = supplierRepository.findById(supplierId).orElseThrow(() -> new AppException(ErrorCode.SUPPLIER_NOT_FOUND.getMsg(), ErrorCode.SUPPLIER_NOT_FOUND));
        // supplier.getProducts().add(product);
        product.setSupplier(supplier);
        return productMapper.toResponse(productRepository.save(product));
    }

    public List<ProductResponse> findAllProducts() {
        List<Product> products = productRepository.findAll();
        return productMapper.toResponse(products);
    }

    public ProductResponse findById(Long id) {
        ProductResponse product = productMapper.toResponse(productRepository.findById(id).orElseThrow());
        return product;
    }

    public List<ProductResponse> findByName(String productName) {
        List<Product> products = productRepository.findByProductNameContains(productName);
        return productMapper.toResponse(products);
    }

    public ProductResponse updateProduct(Long id, ProductCreationRequest request) {
        Product product = productRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND.getMsg(), ErrorCode.PRODUCT_NOT_FOUND));
        Set<Long> categoryIds = request.getCategoryIds();
        for (Long categoryId : categoryIds) {
            Category category = categoryRepository.findById(categoryId).orElseThrow(
                    () -> new AppException(ErrorCode.CATEGORY_NOT_FOUND.getMsg(), ErrorCode.CATEGORY_NOT_FOUND));
            product.getCategories().add(category); 
            // service nào thì add entity khác vào: ví dụ: product service -> add entity category
        }
        Long supplierId = request.getSupplierId();
        if(supplierId != null){
            Supplier supplier = supplierRepository.findById(supplierId).orElseThrow(() -> new AppException(ErrorCode.SUPPLIER_NOT_FOUND.getMsg(), ErrorCode.SUPPLIER_NOT_FOUND));
            // supplier.getProducts().add(product);
            product.setSupplier(supplier);
        }
        productMapper.updateProduct(product, request);
        return productMapper.toResponse(productRepository.save(product));
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(null);
        productRepository.delete(product);
    }
}
