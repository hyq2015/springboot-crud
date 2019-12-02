package springbootcrud.common;

public enum HttpStatus {
    NOT_FOUND(403, "请登录"),UNAUTHORIZED(401, "登录超时,请重新登录"),
    NO_SMS_CODE_RECORD(1001, "未给此邮箱发送过验证码"),
    SMS_CODE_EXPIRED(1002, "验证码已经过期"),
    SMS_CODE_INVALID(1002, "验证码已经过期"),
    SMS_CODE_MISMATCH(1002, "验证码错误"),
    REGISTER_SUCCESS(0, "注册成功");
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
