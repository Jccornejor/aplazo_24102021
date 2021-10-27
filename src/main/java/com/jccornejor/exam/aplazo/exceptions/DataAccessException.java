package com.jccornejor.exam.aplazo.exceptions;

import lombok.Getter;

public class DataAccessException extends Exception {
    @Getter
    private String message;

    @Getter
    private Integer code;

    public DataAccessException(final String message, final Integer code,
                               final Throwable cause) {

        super(message, cause);

        this.message = message;
        this.code = code;
    }

    public DataAccessException(final String message, final Throwable cause) {

        super(message, cause);
        this.message = message;
    }

    public DataAccessException(final String message, final Integer code) {

        super(message);
        this.message = message;
        this.code = code;
    }

    public DataAccessException(final String message) {
        super(message);
        this.message = message;
    }

    public DataAccessException() {
        super();
    }

}