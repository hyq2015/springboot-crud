package springbootcrud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import springbootcrud.dto.UserRegister;

import java.net.UnknownHostException;

@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<Object, UserRegister> userRegisterRedisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<Object, UserRegister> redisTemplate = new RedisTemplate<Object, UserRegister>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<UserRegister> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<UserRegister>(UserRegister.class);
        redisTemplate.setDefaultSerializer(jackson2JsonRedisSerializer);
        return redisTemplate;
    }
}
