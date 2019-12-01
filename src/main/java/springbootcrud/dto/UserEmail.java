package springbootcrud.dto;

import javax.validation.constraints.NotBlank;

public class UserEmail {
    @NotBlank(message = "请输入邮箱")
    private String email;
    private String code;
    private Long createTime;

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
