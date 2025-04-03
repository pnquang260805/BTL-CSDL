package com.ptit.csdl.dto.request.update;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerUpdateRequest {
    String customerName;
    String firstName;
    String lastName;
    String phoneNumber;
    String gender;
    String email;
    String password;
    String address;
    Set<Long>  paymentAccountId;
}
