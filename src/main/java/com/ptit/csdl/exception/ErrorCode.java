package com.ptit.csdl.exception;

public enum ErrorCode {
    CUSTOMER_NOT_EXISTS(10001, "customer not exist"),
    ORDER_NOT_EXISTS(10002, "Order not exist"),
    PRODUCT_NOT_FOUND(10003, "product not found"),
    CATEGORY_NOT_FOUND(10004, "category not found"),
    SUPPLIER_NOT_FOUND(10005, "supplier not found")
    ;
    private Integer code;
    private String msg;
    
    private ErrorCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
}
