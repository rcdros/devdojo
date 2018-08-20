package br.com.devdojo.handler;

import br.com.devdojo.error.ErrorDetails;
import br.com.devdojo.error.ResourceNotFoundDetails;
import br.com.devdojo.error.ResourceNotFoundException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

/**
 * Created by ricardors on 05/08/2018.
 */

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity<?> handlerPropertyReferenceException(PropertyReferenceException exception) {
        ErrorDetails rfnDetails = ErrorDetails.ErrorDetailsBuilder.newBuilder()
                .timeStamp(new Date().getTime())
                .status(HttpStatus.BAD_REQUEST.value())
                .message(exception.getClass().getName())
                .title("Error")
                .Details(exception.getMessage()).build();

        return new ResponseEntity<>(rfnDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handlerResourceNotFoundException(ResourceNotFoundException exception) {
        ResourceNotFoundDetails rfnDetails = ResourceNotFoundDetails.Builder.newBuilder()
                .timeStamp(new Date().getTime())
                .Status(HttpStatus.NOT_FOUND.value())
                .message(exception.getClass().getName())
                .title("Erro")
                .Details(exception.getMessage()).build();

        return new ResponseEntity<>(rfnDetails, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorDetails rfnDetails = ErrorDetails.ErrorDetailsBuilder.newBuilder()
                .timeStamp(new Date().getTime())
                .status(status.value())
                .message(ex.getClass().getName())
                .title("Error")
                .Details(ex.getMessage()).build();
        return new ResponseEntity(rfnDetails, headers, status);
    }


}
