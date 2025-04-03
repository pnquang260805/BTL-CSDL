package com.ptit.csdl.mapper;

import com.ptit.csdl.dto.request.creation.CustomerRegisterRequest;
import com.ptit.csdl.dto.request.update.CustomerUpdateRequest;
import com.ptit.csdl.dto.response.CustomerResponse;
import com.ptit.csdl.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    @Mapping(target = "orders", ignore = true)
    Customer toCustomer(CustomerRegisterRequest request);
    CustomerResponse toCustomerResponse(Customer customer);
    List<CustomerResponse> toCustomerResponse(List<Customer> customers);
    void updateCustomer(@MappingTarget Customer customer, CustomerUpdateRequest request);
}
