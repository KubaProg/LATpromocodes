package eu.sii.promocodes.exception;

import eu.sii.promocodes.exception.BusinessException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

public class RequestNotValidException extends BusinessException {
    public RequestNotValidException(BindingResult bindingResult) {
        super(HttpStatus.BAD_REQUEST, (bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString()));
    }
}
