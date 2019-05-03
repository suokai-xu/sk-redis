package com.sk.service;
/**
* 2019年3月12日 上午11:16:59
* @HXing xu
* sk-redis
* 
**/

import java.util.List;

import com.sk.entity.User;

public interface UserService {

	/**
	 * 
	 * @param userName
	 * @return
	 */
	User getUserById(Integer id);
	
	User getUserByName(String name);
	
	long delUserById(Integer id);

	/**
	 * 
	 * @return
	 */
	List<User> listUser();

}
