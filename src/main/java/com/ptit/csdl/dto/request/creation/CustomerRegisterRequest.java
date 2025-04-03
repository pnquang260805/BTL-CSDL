package com.ptit.csdl.dto.request.creation;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerRegisterRequest {
    String customerName;
    String firstName;
    String lastName;
    String phoneNumber;
    String gender;
    String email;
    String password;
    String address;
    Set<Long> orders;
}