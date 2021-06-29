package com.gamesys.exclusion.exception;


import com.gamesys.exclusion.modal.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class ExclusionException extends Exception {

    private static final long serialVersionId = 1L;
    private ErrorResponse errorResponse;
    private HttpStatus httpStatus;

    public ExclusionException(String exception) { super(exception);}
}
