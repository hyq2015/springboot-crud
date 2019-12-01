package springbootcrud.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserShow {
    @NotBlank(message = "请输入邮箱")
    @Email(message = "邮箱格式错误")
    private String email;

    @NotBlank(message = "请输入用户名")
    private String userName;
    private Integer id;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
