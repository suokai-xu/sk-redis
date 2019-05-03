package com.sk;

import static org.mockito.Mockito.validateMockitoUsage;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import com.sk.entity.User;
import com.sk.mapper.UserMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SkRedisApplicationTests {
	
	 @Autowired
	 private UserMapper userDao;
	 @Autowired
	 private RedisTemplate redisTemplate;
	

//	@Test
//	public void contextLoads() {
//	}
//	
	@Test
	public void get_redis() {
		Integer id = 3;
		 // 从缓存中获取用户信息
        String key = "user_"+id;
        ValueOperations<Object, Object> operations = redisTemplate.opsForValue();
        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
        	User user = (User) operations.get(key);
            System.out.println("UserServiceImpl.getUserById() : 从缓存中获取了用户 >> " + user.toString());
        }else {
        	 // 从 DB 中获取城市信息
            User user = userDao.getUserById(id);;
     
            // 插入缓存
            operations.set(key, user,20, TimeUnit.SECONDS);
            
           
        }
	}
	
	public void del_redis() {
		
	}

}
