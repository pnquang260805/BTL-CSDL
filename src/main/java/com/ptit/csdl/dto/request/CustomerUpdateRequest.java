package com.ptit.csdl.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerUpdateRequest {
    @JsonProperty("customer_name")
    String customerName;
    @JsonProperty("first_name")
    String firstName;
    @JsonProperty("last_name")
    String lastName;
    @JsonProperty("phone_number")
    String phoneNumber;
    String gender;
    String email;
    String password;
    @JsonProperty("address_id")
    Set<Long> addressId;
    @JsonProperty("payment_account_id")
    Set<Long>  paymentAccountId;
}
