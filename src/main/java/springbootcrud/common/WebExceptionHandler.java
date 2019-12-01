package springbootcrud.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@ControllerAdvice
@ResponseBody
public class WebExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(WebExceptionHandler.class);

    /**
     * jwt token过期handler
     * @param e ExpiredJwtException
     * @return {Result}
     */
    @ExceptionHandler(value = {TokenException.class})
    public Result tokenExpiredHandler(TokenException e) {
        return ResultUtil.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = {BindResultException.class})
    public Result bindExceptionHandler(BindResultException e) {
        List<ObjectError> allErrors = e.getBindException().getAllErrors();
        StringBuilder s = new StringBuilder();
        for (ObjectError error : allErrors) {
            s.append(error.getDefaultMessage()).append(',');
        }
        s.deleteCharAt(s.length() - 1);
        return ResultUtil.error(1, s.toString());
    }
}
