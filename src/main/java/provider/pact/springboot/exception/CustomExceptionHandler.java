package provider.pact.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorModel>> handleFieldValidation(MethodArgumentNotValidException manv) {
        List<ErrorModel> errorModelList = new ArrayList<>();
        List<FieldError> fieldErrorList = manv.getBindingResult().getFieldErrors();
        ErrorModel errorModel = null;

        for (FieldError error : fieldErrorList) {
            errorModel = new ErrorModel();
            errorModel.setCode(error.getField());
            errorModel.setErrorMessage(error.getDefaultMessage());
            errorModelList.add(errorModel);
        }
        return new ResponseEntity<>(errorModelList, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<List<ErrorModel>> handleBusinessException(BusinessException bex) {
        return new ResponseEntity<>(bex.getErrors(), HttpStatus.UNAUTHORIZED);
    }
}
