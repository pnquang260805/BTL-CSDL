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
public class OrderCreationRequest {
    Date orderDate;
    Float totalAmount;
    String status;
    Long numberOfProductsOrdered;
    Long customerId;
    Set<Long> productId;
    Long paymentAccountId;
}
