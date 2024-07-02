package com.scccy.videoBase.handlerExption;

import java.io.IOException;

public class CustomExceptions {
    // 自定义异常类
    public static class CustomException extends RuntimeException {
        public CustomException(String message) {
            super(message);
        }

        public CustomException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static class APINotFoundException extends CustomException {
        public APINotFoundException(String message) {
            super(message);
        }
    }

    public static class APITimeoutException extends CustomException {
        public APITimeoutException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static class APIConnectionException extends CustomException {
        public APIConnectionException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static class APIUnauthorizedException extends CustomException {
        public APIUnauthorizedException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static class APIResponseException extends CustomException {
        public APIResponseException(String message) {
            super(message);
        }

        public APIResponseException(String message, Throwable cause) {
            super(message, cause);
        }
    }
    public static void handleException(Exception e, String url) throws CustomExceptions.APITimeoutException, CustomExceptions.APIConnectionException, CustomExceptions.APIUnauthorizedException, CustomExceptions.APIResponseException {
        if (e instanceof IOException) {
            String message = e.getMessage();
            if (message.contains("timeout")) {
                throw new CustomExceptions.APITimeoutException("Request timed out for URL: " + url, e);
            } else if (message.contains("connection")) {
                throw new CustomExceptions.APIConnectionException("Connection error for URL: " + url, e);
            } else if (message.contains("unauthorized")) {
                throw new CustomExceptions.APIUnauthorizedException("Unauthorized request for URL: " + url, e);
            } else {
                throw new CustomExceptions.APIResponseException("Unexpected error for URL: " + url, e);
            }
        } else {
            throw new CustomExceptions.APIResponseException("Unexpected error for URL: " + url, e);
        }
    }

    public class APIResponseError extends Throwable {
    }
}
