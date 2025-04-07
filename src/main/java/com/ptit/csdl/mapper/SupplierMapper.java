package com.ptit.csdl.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.ptit.csdl.dto.request.creation.SupplierCreationRequest;
import com.ptit.csdl.dto.request.update.SupplierUpdateRequest;
import com.ptit.csdl.dto.response.SupplierResponse;
import com.ptit.csdl.entity.Supplier;

@Mapper(componentModel = "spring")
public interface SupplierMapper {
    public Supplier toSupplier(SupplierCreationRequest request);
    public SupplierResponse toSupplierResponse(Supplier supplier);
    public List<SupplierResponse> toSupplierResponse(List<Supplier> supplier);

    public void updateSupplier(@MappingTarget Supplier supplier, SupplierUpdateRequest request);
}
