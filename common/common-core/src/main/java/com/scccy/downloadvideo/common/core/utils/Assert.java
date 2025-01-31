public class Assert {
    
    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new ServiceException(ErrorCode.PARAM_ERROR.getCode(), message);
        }
    }
    
    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new ServiceException(ErrorCode.PARAM_ERROR.getCode(), message);
        }
    }
    
    public static void hasText(String text, String message) {
        if (text == null || text.trim().isEmpty()) {
            throw new ServiceException(ErrorCode.PARAM_ERROR.getCode(), message);
        }
    }
} 