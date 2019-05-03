package com.sk.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.sk.entity.User;
import com.sk.mapper.UserMapper;
import com.sk.service.UserService;


/**
* 2019年3月12日 上午11:18:07
* @HXing xu
* sk-redis
* 
**/
@Service
public class UserServiceImpl implements UserService {

	 
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	 @Autowired
	 private UserMapper userDao;
	 @Autowired
	 private RedisTemplate redisTemplate;
	 @Autowired
	 private StringRedisTemplate stringRedisTemplate;
	 
	
	@Override
	public User getUserById(Integer id) {
		// TODO Auto-generated method stub\
		
		 // 从缓存中获取用户信息
        String key = "user_"+id;
        ValueOperations<Object, Object> operations = redisTemplate.opsForValue();
        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
        	User user = (User) operations.get(key);
            log.info("UserServiceImpl.getUserById() : 从缓存中获取了用户 >> " + user.toString());
            return user;
        }else {
        	 // 从 DB 中获取城市信息
            User user = userDao.getUserById(id);;
     
            // 插入缓存
            operations.set(key, user , 30 , TimeUnit.MINUTES);

    		return user;
        }
       
	}

	@Override
	public List<User> listUser() {
		String key = "list_user";
		ListOperations<Object, Object> operations = redisTemplate.opsForList();
        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if(hasKey) {
        	Long i = operations.size(key);
        	List<Object> list = operations.range(key, 0, operations.size(key));
            log.info("UserServiceImpl.getUserById() : 从缓存中获取了用户 >> " + list.toString());
            
            List<User> u_List = new ArrayList<>();
            // 将object 转为 实体类
            for(Object user : list) {
            	User us = new User();
            	us = (User) user;
            	u_List.add(us);
            }
            return u_List;
            
        }else { 
        	List<User> list = userDao.selectAll();
        	for(User user : list) {
        		operations.leftPush(key, user);
        	}
        	
        	return list;
        }
		
	}

	@Override
	public User getUserByName(String name) {
		// TODO Auto-generated method stub
		return userDao.getUserByName(name);
	}
	
	 @Override
    public long delUserById(Integer id) {
		 Long l = userDao.delUserById(id);
        //如果缓存存在，删除缓存
        String key = "user_" +id;
        boolean haskey = redisTemplate.hasKey(key);
        if(haskey) {
            redisTemplate.delete(key);
        }
        return 1;
    }

}
