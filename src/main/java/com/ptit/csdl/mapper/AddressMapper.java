package com.ptit.csdl.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ptit.csdl.dto.request.AddressCreationRequest;
import com.ptit.csdl.dto.response.AddressResponse;
import com.ptit.csdl.entity.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    @Mapping(target = "id", source = "id")
    AddressResponse toResponse(Address address);
    
    Address toAddress(AddressCreationRequest request);
    List<AddressResponse> toResponse(List<Address> address);
}
