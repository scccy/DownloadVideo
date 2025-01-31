@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private String code;
    private String message;
    private Object details;
    
    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
} 