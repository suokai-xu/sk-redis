package com.sk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.sk.entity.User;
import com.sk.utils.MyMapper;



public interface UserMapper extends MyMapper<User> {
	
	@Select("select * from user where id=#{id} ")
	User getUserById(Integer id);
	
	@Select("delete from user where id=#{id} ")
	Long delUserById(Integer id);
	
	@Select("select * from user where name=#{name} ")
	User getUserByName(String name);
	
	@Select("select * from user")
	List<User> listUser();
}
