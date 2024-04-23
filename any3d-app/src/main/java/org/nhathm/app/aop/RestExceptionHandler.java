package org.nhathm.app.aop;

import com.alibaba.cola.catchlog.ResponseHandlerI;
import com.alibaba.cola.dto.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nhathm.domain.auth.domainservice.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import util.StringUtils;
import util.error.ThirdServiceException;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {

    private final ResponseHandlerI colaResponseHandler;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> resolveException(Exception ex) {
        Object response = colaResponseHandler.handle(Response.class, "SYS-ERROR-500", "Internal Server Error. " + ex.getMessage());
        log.error("Internal Server Error.", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> resolveValidationException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        String message = StringUtils.join(fieldErrors.stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.toList()), ", ");
        Object response = colaResponseHandler.handle(Response.class, "REQ-ERROR-400", message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> resolveMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        Object response = colaResponseHandler.handle(Response.class, "REQ-ERROR-405", "Method Not Allowed.");
        log.error("Method Not Allowed.", ex);
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(response);
    }

    @ExceptionHandler(ThirdServiceException.class)
    public ResponseEntity<?> resolveThirdServiceException(ThirdServiceException ex) {
        Object response = colaResponseHandler.handle(Response.class, ex.getErrCode(), ex.getMessage());
        log.error("Third Service Exception.", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<?> resolveUnauthorizedException(UnauthorizedException ex) {
        Object response = colaResponseHandler.handle(Response.class, ex.getErrCode(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
}