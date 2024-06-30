package com.scccy.videoBase.handlerExption;

public class CustomExceptions {
    // 自定义异常类
    public static class CustomException extends RuntimeException {
        public CustomException(String message) {
            super(message);
        }
    }
    public static class APITimeoutException extends Exception {
        public APITimeoutException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static class APIConnectionException extends Exception {
        public APIConnectionException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static class APIUnauthorizedException extends RuntimeException {
        public APIUnauthorizedException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
