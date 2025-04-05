package com.ptit.csdl.entity;

import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class CartProductId {
    private Long productId;
    private Long cartId;
    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(!(obj instanceof CartProductId))
            return false;
        CartProductId that = (CartProductId) obj;
        return Objects.equals(cartId, that.cartId) && Objects.equals(productId, that.productId);
    }
    @Override
    public int hashCode() {
        return Objects.hash(productId, cartId);
    }    
}
