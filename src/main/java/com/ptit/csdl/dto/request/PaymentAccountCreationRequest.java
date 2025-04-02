package com.ptit.csdl.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentAccountCreationRequest {
    String provider;
    String accountNumber;
    Long userId;
    Set<Long> orderId;
}
