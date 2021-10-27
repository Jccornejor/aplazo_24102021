package com.jccornejor.exam.aplazo.exceptions;

import lombok.Getter;

public class BusinessException extends Exception {
    @Getter
    private String message;

    @Getter
    private Integer code;

    public BusinessException(final String message, final Integer code,
                             final Throwable cause) {

        super(message, cause);

        this.message = message;
        this.code = code;
    }

    public BusinessException(final String message, final Throwable cause) {

        super(message, cause);
        this.message = message;
    }

    public BusinessException(final String message, final Integer code) {

        super(message);
        this.message = message;
        this.code = code;
    }

    public BusinessException(final String message) {
        super(message);
        this.message = message;
    }

    public BusinessException() {
        super();
    }
}
