package springbootcrud.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "userModel")
public class User {
    @ApiModelProperty(name = "age", value = "用户年龄", required = true)
    private Integer age;
    @ApiModelProperty(name = "name", value = "用户姓名", required = true)
    private String name;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
