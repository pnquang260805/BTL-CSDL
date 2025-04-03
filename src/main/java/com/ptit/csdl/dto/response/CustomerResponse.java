package com.ptit.csdl.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerResponse {
    Long id;
    String customerName;
    String firstName;
    String lastName;
    String phoneNumber;
    String gender;
    String email;
    String password;
    String address;
    Set<OrderResponse> orders;
}
