package springbootcrud.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import springbootcrud.bean.User;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM USER")
    public List<User> getAllUser();

    @Insert("INSERT INTO user(name, age) values(#{name}, #{age})")
    public void addUser(User user);
}
