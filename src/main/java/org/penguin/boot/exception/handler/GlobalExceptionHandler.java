package org.penguin.boot.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.penguin.boot.result.DataWrapper;
import org.penguin.boot.result.ExceptionResponse;
import org.penguin.boot.result.ReturnCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class, BindException.class,
            MethodArgumentNotValidException.class})
    public ResponseEntity<DataWrapper> validationExceptionHandler(Exception exception) {
        BindingResult bindResult = null;
        StringBuilder msg = new StringBuilder();
        if (exception instanceof ConstraintViolationException) {
            msg = msg.append(exception.getMessage());
        } else if (exception instanceof BindException) {
            bindResult = ((BindException) exception).getBindingResult();
        } else if (exception instanceof MethodArgumentNotValidException) {
            bindResult = ((MethodArgumentNotValidException) exception).getBindingResult();
        }

        if (bindResult != null && bindResult.hasErrors()) {
            log.error("Validation Binding Exception: {}", bindResult.getAllErrors());
            for (ObjectError objectError : bindResult.getAllErrors()) {
                FieldError fieldError = (FieldError) objectError;
                msg = msg.append(fieldError.getField()).append(": ")
                        .append(fieldError.getDefaultMessage()).append(", ");
            }
        }
        log.error("Validation Exception: {}", msg);
        return ResponseEntity
                .ok(new DataWrapper(ReturnCode.INVALID_PARAMETER, msg.toString(), ""));
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    ResponseEntity<?> handleControllerException(HttpServletRequest request, Throwable ex) {
        HttpStatus status = getStatus(request);
        log.error("Global Internal Runtime Error: ", ex);
        return new ResponseEntity<>(ExceptionResponse.builder().code(status.value()).message(ex.getMessage()), status);
    }
}
