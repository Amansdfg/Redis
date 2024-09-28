package kz.kalabay.redis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import kz.kalabay.redis.model.Article;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig{
    @Value("${spring.data.redis.host}")
    private String HOST;
    @Value("${spring.data.redis.port}")
    private Integer PORT;
    @Bean
    public RedisConnectionFactory createConnection(){
        return new LettuceConnectionFactory(HOST,PORT);
    }
    @Bean
    public RedisTemplate<String,Article> articleRedisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String,Article> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

}