package springbootcrud.bean;

import io.swagger.annotations.ApiModelProperty;
public class UserAddModel extends User {
    @ApiModelProperty(value = "用户id")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
