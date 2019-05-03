package com.sk.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sk.entity.User;
import com.sk.socket.MyWebSocket;
import com.sk.utils.JsonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
* 2019年3月26日 下午3:31:42
* @HXing xu
* sk-redis
* 
**/

@Api("MyHtmlController--WebSocket--测试API")
@Controller
public class MyHtmlController {
	
	private static final Logger log = LoggerFactory.getLogger(MyHtmlController.class);

	/**
	 * 查询用户列表
	 * @return
	 */
	@ApiOperation(value="websocket一个小测试", notes="小小测试")
	@RequestMapping(value = "ht1", method = RequestMethod.GET)
	public String MyHt1 (){
		log.info("获取页面index1");
		
		return "index1";
	}
	
	/**
	 * 查询用户列表
	 * @return
	 */
	@ApiOperation(value="websocket一个小测试", notes="小小测试")
	@RequestMapping(value = "ht/{name}", method = RequestMethod.GET)
	public String MyHt (@PathVariable String name,Model model){
		log.info("获取页面index");

        model.addAttribute("username",name);
		return "index";
	}
	

}
