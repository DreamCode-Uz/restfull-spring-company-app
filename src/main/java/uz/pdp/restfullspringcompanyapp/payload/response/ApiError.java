package uz.pdp.restfullspringcompanyapp.payload.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
public class ApiError {
    private HttpStatus status;
    private String message;
    private List<String> errors;

    public ApiError(HttpStatus status, String message, List<String> errors) {
        this.message = message;
        this.status = status;
        this.errors = errors;
    }

    public ApiError(HttpStatus status, String message, String errors) {
        this.message = message;
        this.status = status;
        this.errors = Collections.singletonList(errors);
    }
}
