package com.ptit.csdl.dto.request.creation;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerRegisterRequest {
    @JsonProperty("customer_name")
    String customerName;
    @JsonProperty("first_name")
    String firstName;
    @JsonProperty("last_name")
    String lastName;
    @JsonProperty("phone_number")
    String phoneNumber;
    @JsonProperty("gender")
    String gender;
    String email;
    String password;
    String address;
    Set<Long> orders;
}