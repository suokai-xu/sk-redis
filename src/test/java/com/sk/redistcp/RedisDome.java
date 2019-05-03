package com.sk.redistcp;

import java.io.IOException;
import java.net.UnknownHostException;

/**
* 2019年5月3日 上午10:45:41
* @HXing xu
* sk-redis
* 
**/

public class RedisDome {

	
	public static void main (String [] agre) throws UnknownHostException, IOException {
		RedisClient r = new RedisClient("localhost", 6379);
		System.out.println(r.toString());
		r.set("xu", "123");
		
		String string = r.get("xu");
		System.out.println(string);
	}

}
