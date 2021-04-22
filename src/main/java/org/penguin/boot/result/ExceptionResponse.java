package org.penguin.boot.result;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
public class ExceptionResponse {
    private int code;
    private String message;
    private final Date dateTime = new Date();
}