package com.scccy.videoBase.handlerExption;

import okhttp3.Response;

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

    public static class APIUnavailableException extends CustomException {
        public APIUnavailableException(String message) {
            super(message);
        }
    }

    public static class APIRateLimitException extends CustomException {
        public APIRateLimitException(String message) {
            super(message);
        }
    }

    public static class APIRetryExhaustedException extends CustomException {
        public APIRetryExhaustedException(String message) {
            super(message);
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

    // 新增的异常处理方法
    public static void handleHttpStatusError(Response response, int attempt) throws CustomExceptions.APINotFoundException, CustomExceptions.APIUnavailableException, CustomExceptions.APITimeoutException, CustomExceptions.APIUnauthorizedException, CustomExceptions.APIRateLimitException, CustomExceptions.APIResponseException {
        int statusCode = response.code();
        switch (statusCode) {
            case 404:
                throw new CustomExceptions.APINotFoundException("HTTP status code 404: Not Found");
            case 503:
                throw new CustomExceptions.APIUnavailableException("HTTP status code 503: Service Unavailable");
            case 408:
                throw new CustomExceptions.APITimeoutException("HTTP status code 408: Request Timeout", new Throwable("HTTP status code 408: Request Timeout"));
            case 401:
                throw new CustomExceptions.APIUnauthorizedException("HTTP status code 401: Unauthorized", new Throwable("HTTP status code 401: Unauthorized"));
            case 429:
                throw new CustomExceptions.APIRateLimitException("HTTP status code 429: Too Many Requests");
            default:
                throw new CustomExceptions.APIResponseException("HTTP status code " + statusCode + ": Unexpected status");
        }
    }

}