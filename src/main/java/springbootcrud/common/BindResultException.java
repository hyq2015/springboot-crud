package springbootcrud.common;

import org.springframework.validation.BindingResult;

public class BindResultException extends RuntimeException {
    private BindingResult bindException;

    public BindingResult getBindException() {
        return bindException;
    }

    public BindResultException(BindingResult bindException) {
        this.bindException = bindException;
    }
}
