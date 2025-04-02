package com.ptit.csdl.exception;

public class AppException extends RuntimeException{
    private ErrorCode errorCode;
    public AppException(String string, ErrorCode errorCode){
        super(errorCode.getMsg());
        this.errorCode = errorCode;
    }
    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
    
}
