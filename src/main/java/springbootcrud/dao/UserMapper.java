package springbootcrud.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import springbootcrud.bean.User;
import springbootcrud.bean.UserAddModel;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user")
    public List<User> getAllUser();

    // 自动将id设置到传入的user中
    @Insert("INSERT INTO user(name, age) values(#{name}, #{age})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void addUser(UserAddModel user);
}
