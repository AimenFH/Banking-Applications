package fh_campuswien.banking_applications.accounts.dto;


import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorResponseDto {

    public ErrorResponseDto(String apiPath, HttpStatus errorCode, String errorMessage, LocalDateTime errorTime) {
        this.apiPath = apiPath;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorTime = errorTime;
    }

    private String apiPath;
    private HttpStatus errorCode;
    private String errorMessage;
    private LocalDateTime errorTime;
}
