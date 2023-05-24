package ru.voronovskii.exception;

import lombok.Getter;

public class UserServiceException extends RuntimeException {

    @Getter
    private final String errorCode;

    public UserServiceException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public UserServiceException(ErrorMessages error) {
        super(error.getErrorCause());
        this.errorCode = error.getErrorCode();
    }

    public UserServiceException(ErrorMessages error, Object data) {
        super(error.getErrorCause() + data);
        this.errorCode = error.getErrorCode();
    }
}
