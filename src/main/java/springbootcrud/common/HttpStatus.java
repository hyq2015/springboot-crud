package springbootcrud.common;

public enum HttpStatus {
    NOT_FOUND(403, "请登录"),UNAUTHORIZED(401, "登录超时,请重新登录");
    private Integer code;
    private String message;

    HttpStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
