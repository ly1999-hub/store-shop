package online.shop.store.controllers.exception;

import java.io.FileNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@RestControllerAdvice
public class HandlerException {

    private static final Logger LOG = LoggerFactory.getLogger(HandlerException.class);
    
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<String> handleMaxSizeException(MaxUploadSizeExceededException exc) {
        LOG.error(exc.getMessage(), exc);
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("File quá lớn!");
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<String> handleFileNotFoundException(FileNotFoundException exc) {
        LOG.error(exc.getMessage());
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("khong tim thay file");
    }

    @ExceptionHandler(NullPointerException.class)
        public ResponseEntity<String> handleNullPointerException(NullPointerException exc) {
            LOG.error(exc.getMessage(), exc);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("lỗi hệ thống");
        }    
    
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException exc) {
        LOG.error(exc.getMessage(), exc);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("giá trị không tồn tại");
    }
    
    // Là tập hợp các lỗi chung cho tất cả các lỗi truy cập dữ liệu 
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<String> handleDataAccessException(DataAccessException exc){
        LOG.error(exc.getMessage(), exc);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi Server");
    }
     
    // Truy vấn mong đợi là một nhưng trả về n>1 ...
    @ExceptionHandler(IncorrectResultSizeDataAccessException.class)
    public ResponseEntity<String> handleIncorrectResultSizeDataAccessException(IncorrectResultSizeDataAccessException exc){
        LOG.error(exc.getMessage(), exc);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi hệ thống. mời bạn thử lại");
    }
}