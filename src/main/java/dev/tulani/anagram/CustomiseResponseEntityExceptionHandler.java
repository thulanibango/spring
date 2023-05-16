package dev.tulani.anagram;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDateTime;

@ControllerAdvice
public class CustomiseResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
//    This class handles Custom responses from requests made, ResponseEntityExceptionHandler is an in bui
//    response and CustomiseResponseEntityExceptionHandler extends from it to Customise it
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request){
        ErrorDetails errorDetails= new ErrorDetails(LocalDateTime.now(),
                ex.getMessage(), request.getDescription(false));
    return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AnagramNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleAnagramNotFoundException(Exception ex, WebRequest request){
        ErrorDetails errorDetails= new ErrorDetails(LocalDateTime.now(),
                ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
    }
}
