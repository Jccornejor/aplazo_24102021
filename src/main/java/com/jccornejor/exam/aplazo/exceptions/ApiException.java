package com.jccornejor.exam.aplazo.exceptions;

import lombok.Getter;

public class ApiException extends Exception {
    @Getter
    private String message;

    @Getter
    private Integer code;

    public ApiException(final String message, final Integer code,
                        final Throwable cause) {

        super(message, cause);

        this.message = message;
        this.code = code;
    }

    public ApiException(final String message, final Throwable cause) {

        super(message, cause);
        this.message = message;
    }

    public ApiException(final String message, final Integer code) {

        super(message);
        this.message = message;
        this.code = code;
    }

    public ApiException(final String message) {
        super(message);
        this.message = message;
    }

    public ApiException() {
        super();
    }

}