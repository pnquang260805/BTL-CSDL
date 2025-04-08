package com.ptit.csdl.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;

import com.ptit.csdl.entity.Cart;
import com.ptit.csdl.entity.Customer;
import com.ptit.csdl.entity.Product;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {
    Long id;
    Date orderDate;
    Float totalAmount;
    String status;
    Long numberOfProductsOrdered;
    Long customerId;
    Set<Cart> carts;
}
