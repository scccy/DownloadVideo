package com.scccy.downloadvideo.common.core.exception;

import com.scccy.downloadvideo.common.core.enums.ErrorCodeEnum;
import okhttp3.Response;

public class CustomExceptions {
    
    public static class CustomException extends RuntimeException {
        private final String code;

        public CustomException(ErrorCodeEnum errorCode) {
            super(errorCode.getMessage());
            this.code = errorCode.getCode();
        }

        public String getCode() {
            return code;
        }
    }

    public static class APINotFoundException extends CustomException {
        public APINotFoundException() {
            super(ErrorCodeEnum.NOT_FOUND);
        }
    }

    public static class APITimeoutException extends CustomException {
        public APITimeoutException() {
            super(ErrorCodeEnum.SYSTEM_ERROR);
        }
    }

    public static class APIConnectionException extends CustomException {
        public APIConnectionException() {
            super(ErrorCodeEnum.SYSTEM_ERROR);
        }
    }

    public static class APIUnauthorizedException extends CustomException {
        public APIUnauthorizedException() {
            super(ErrorCodeEnum.UNAUTHORIZED);
        }
    }

    public static class APIResponseException extends CustomException {
        public APIResponseException() {
            super(ErrorCodeEnum.SYSTEM_ERROR);
        }
    }

    public static class APIUnavailableException extends CustomException {
        public APIUnavailableException() {
            super(ErrorCodeEnum.SYSTEM_ERROR);
        }
    }

    public static class APIRateLimitException extends CustomException {
        public APIRateLimitException() {
            super(ErrorCodeEnum.TOO_MANY_REQUESTS);
        }
    }

    public static class APIResponseError extends CustomException {
        public APIResponseError() {
            super(ErrorCodeEnum.PARSE_ERROR);
        }
    }

    /**
     * 处理HTTP状态码错误
     */
    public static void handleHttpStatus(Response response) {
        int statusCode = response.code();
        switch (statusCode) {
            case 404:
                throw new APINotFoundException();
            case 503:
                throw new APIUnavailableException();
            case 408:
                throw new APITimeoutException();
            case 401:
                throw new APIUnauthorizedException();
            case 429:
                throw new APIRateLimitException();
            default:
                if (statusCode >= 400) {
                    throw new APIResponseException();
                }
        }
    }
}