package springbootcrud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import springbootcrud.bean.User;
import springbootcrud.bean.UserAddModel;
import springbootcrud.dao.UserMapper;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 将方法的运行结果缓存起来，以后要是有相同的数据，直接从缓存中取出，不用再调用方法
     * @return {List} List<User>
     */
     @Cacheable(cacheNames = {"user"}, key = "")
    public List<User> getUsers() {
        return userMapper.getAllUser();
    }

    @Async
    public void addUser(UserAddModel user) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        userMapper.addUser(user);
    }
}
