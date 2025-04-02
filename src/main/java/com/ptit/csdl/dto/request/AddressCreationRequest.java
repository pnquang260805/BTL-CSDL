package com.ptit.csdl.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressCreationRequest {
    String houseNumber;
    String street;
    String district;
    String country;
    String zipcode;
    Set<Long> customerId;
}
