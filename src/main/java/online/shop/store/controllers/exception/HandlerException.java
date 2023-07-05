package online.shop.store.controllers.exception;

import java.io.FileNotFoundException;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@RestControllerAdvice
public class HandlerException {
    
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<String> handleMaxSizeException(MaxUploadSizeExceededException exc) {
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("File quá lớn!");
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<String> handleFileNotFoundException(FileNotFoundException exc) {
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("khong tim thay file");
    }

    @ExceptionHandler(NullPointerException.class)
        public ResponseEntity<String> handleNullPointerException(NullPointerException exc) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(exc.getMessage());
        }    
    
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException exc) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
    }
        
        
}