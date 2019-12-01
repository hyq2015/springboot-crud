package springbootcrud.dto;

import javax.validation.constraints.NotBlank;

public class UserRegister extends UserShow {
    @NotBlank(message = "请输入密码")
    private String password;

    @NotBlank(message = "请输入验证码")
    private String smsCode;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }
}
