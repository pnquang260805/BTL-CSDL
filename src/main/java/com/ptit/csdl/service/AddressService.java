package com.ptit.csdl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptit.csdl.dto.request.AddressCreationRequest;
import com.ptit.csdl.dto.response.AddressResponse;
import com.ptit.csdl.mapper.AddressMapper;
import com.ptit.csdl.repository.AddressRepository;
import com.ptit.csdl.entity.Address;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AddressMapper addressMapper;

    public AddressResponse createAddress(AddressCreationRequest request){
        Address address = addressMapper.toAddress(request);
        Address savedAddress = addressRepository.save(address);
        return addressMapper.toResponse(savedAddress);
    }

    public List<AddressResponse> getAddresses(){
        return addressMapper.toResponse(addressRepository.findAll());
    }

    public List<AddressResponse> getStreet(String street){
        List<Address> address = addressRepository.findByStreetContains(street);
        return addressMapper.toResponse(address);
    }
}
