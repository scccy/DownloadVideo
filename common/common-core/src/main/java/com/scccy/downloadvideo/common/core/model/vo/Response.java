@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {
    private String code;
    private String message;
    private T data;
    
    public static <T> Response<T> success(T data) {
        return Response.<T>builder()
            .code("0")
            .message("success")
            .data(data)
            .build();
    }
    
    public static <T> Response<T> error(String code, String message) {
        return Response.<T>builder()
            .code(code)
            .message(message)
            .build();
    }
} 