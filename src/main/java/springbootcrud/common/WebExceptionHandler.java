package springbootcrud.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
