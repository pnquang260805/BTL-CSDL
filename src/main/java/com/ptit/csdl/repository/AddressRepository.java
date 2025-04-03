package com.ptit.csdl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ptit.csdl.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{
    List<Address> findByStreetContains(String street);
}
