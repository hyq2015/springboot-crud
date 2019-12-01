package springbootcrud.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import springbootcrud.dto.UserEmail;
import springbootcrud.dto.UserRegister;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface LoginMapper {
//    @Insert("INSERT INTO user(user_name,email,password,sms_code) values(#{userName},#{email},#{password},#{smsCode})")
    public void registerUser(UserRegister userRegister);

//    @Select("SELECT * FROM user WHERE user_name=#{userName} AND password=#{password}")
//    @Options(useGeneratedKeys = true, keyProperty = "id")
    public UserRegister login(UserRegister user);
    public void sendVerifyCode(UserEmail userEmail);
    public List<UserRegister> validateEmail(UserEmail userEmail);
    public List<UserEmail> getSpecifiedEmail(UserEmail userEmail);
}
