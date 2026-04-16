package org.example.springboot3.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/redis")
public class RedisController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    //存储数据
    @PostMapping("/set")
    public String set(@RequestParam String key,@RequestParam String value) {
        redisTemplate.opsForValue().set(key, value);
        return "success";
    }

    //获取数据
    @GetMapping("/get")
    public String get(@RequestParam String key) {
        Object value = redisTemplate.opsForValue().get(key);
        if (value != null) {
            return value.toString();
        }
        return "error";
    }

    
}
