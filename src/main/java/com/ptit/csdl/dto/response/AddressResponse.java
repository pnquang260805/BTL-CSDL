package com.ptit.csdl.dto.response;

import java.util.Set;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {
    Integer id;
    String houseNumber;
    String street;
    String district;
    String country;
    String zipcode;
    Set<CustomerResponse> customerResponses;
}
