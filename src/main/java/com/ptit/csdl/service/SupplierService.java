package com.ptit.csdl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptit.csdl.dto.request.creation.SupplierCreationRequest;
import com.ptit.csdl.dto.request.update.SupplierUpdateRequest;
import com.ptit.csdl.dto.response.SupplierResponse;
import com.ptit.csdl.entity.Supplier;
import com.ptit.csdl.exception.AppException;
import com.ptit.csdl.exception.ErrorCode;
import com.ptit.csdl.mapper.SupplierMapper;
import com.ptit.csdl.repository.SupplierRepository;

@Service
public class SupplierService {
    @Autowired
    private SupplierMapper supplierMapper;
    @Autowired
    private SupplierRepository supplierRepository;

    public SupplierResponse createSupplier(SupplierCreationRequest request) {
        Supplier supplier = supplierMapper.toSupplier(request);
        return supplierMapper.toSupplierResponse(supplierRepository.save(supplier));
    }

    public List<SupplierResponse> getAllSuppliers() {
        List<Supplier> suppliers = supplierRepository.findAll();
        return supplierMapper.toSupplierResponse(suppliers);
    }

    public SupplierResponse getSupplierById(Long id) {
        return supplierMapper.toSupplierResponse(supplierRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.SUPPLIER_NOT_FOUND.getMsg(), ErrorCode.SUPPLIER_NOT_FOUND)));
    }

    public SupplierResponse updateSupplier(Long id, SupplierUpdateRequest request){
        Supplier supplier = supplierRepository.findById(id).orElseThrow(
            () -> new AppException(ErrorCode.SUPPLIER_NOT_FOUND.getMsg(), ErrorCode.SUPPLIER_NOT_FOUND));
        supplierMapper.updateSupplier(supplier, request);
        return supplierMapper.toSupplierResponse(supplierRepository.save(supplier));
    }

    public List<SupplierResponse> deleteSupplier(Long id){
        Supplier supplier = supplierRepository.findById(id).orElseThrow(
            () -> new AppException(ErrorCode.SUPPLIER_NOT_FOUND.getMsg(), ErrorCode.SUPPLIER_NOT_FOUND));
        supplierRepository.delete(supplier);
        return getAllSuppliers();
    }
}
