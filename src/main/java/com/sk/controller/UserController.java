package com.sk.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sk.entity.User;
import com.sk.service.UserService;
import com.sk.utils.JsonResult;
import com.github.pagehelper.Page;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api("UserController--用户API")
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);


//	@ApiOperation(value="获取用户列表信息", notes="根据url来获取用户列表信息")
//	@RequestMapping(value = "listUser", method = RequestMethod.GET)
////	@RequestMapping("listUser")
//	public Page getListUser(@RequestParam(required = true, defaultValue = "1") Integer start,
//		@RequestParam(required = false, defaultValue = "10") Integer pageSize) {
//			int pageNum = start == null ? 1 : start;
//			pageSize = pageSize == null ? 10 : pageSize;
//			PageHelper.startPage(pageNum, pageSize);
//			List<User> list = userService.listUser();
//			PageInfo pageInfo = new PageInfo(list);
//			Page page = (Page) list;
//			return page;
//	}

	/**
	 * 查询用户列表
	 * @return
	 */
	@ApiOperation(value="获取用户列表", notes="获取用户列表")
	@RequestMapping(value = "users", method = RequestMethod.GET)
	public ResponseEntity<JsonResult> getUserList (){
		log.info("获取用户列表信息");
		JsonResult r = null;
		try {
			List<User> userList = new ArrayList<User>(userService.listUser());
			r = new JsonResult(userList, "ok");
		} catch (Exception e) {
			r = new JsonResult(e.getClass().getName() + ":" + e.getMessage());
			e.printStackTrace();
		}
		return ResponseEntity.ok(r);
	}
	
	/**
	 * 根据ID查询用户
	 * @param id
	 * @return
	 */
	@ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
	@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer", paramType = "path")
	@RequestMapping(value = "getUserById/{id}", method = RequestMethod.GET)
	public  ResponseEntity<JsonResult> getUserById (@PathVariable(value = "id") Integer id) {
		log.info("根据ID获取用户信息");
		JsonResult r = null;
		try {
			User user = userService.getUserById(id);
			r = new JsonResult(user, "ok");
			
		} catch (Exception e) {
			r = new JsonResult(e.getClass().getName() + ":" + e.getMessage());
			e.printStackTrace();
		}
		return ResponseEntity.ok(r);
	}
	
	
	/**
	 * 根据ID删除用户
	 * @param id
	 * @return
	 */
	@ApiOperation(value="根据ID删除用户信息", notes="根据url的id来删除用户信息")
	@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer", paramType = "path")
	@RequestMapping(value = "delUserById/{id}", method = RequestMethod.GET)
	public  ResponseEntity<JsonResult> delUserById (@PathVariable(value = "id") Integer id) {
		log.info("根据ID删除用户信息！！！");
		JsonResult r = null;
		try {
			long l = userService.delUserById(id);
			r = new JsonResult(l, "ok");
			
		} catch (Exception e) {
			r = new JsonResult(e.getClass().getName() + ":" + e.getMessage());
			e.printStackTrace();
		}
		return ResponseEntity.ok(r);
	}
}
