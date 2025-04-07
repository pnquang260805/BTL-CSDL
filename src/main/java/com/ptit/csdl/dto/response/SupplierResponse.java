package com.ptit.csdl.dto.response;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SupplierResponse {
    @JsonProperty("supplier_id")
    Long id;
    @JsonProperty("supplier_name")
    String supplierName;
    @JsonProperty("products")
    Set<ProductResponse> products;
}
