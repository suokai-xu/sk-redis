package com.sk.redistcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;


/**
* 2019年5月3日 上午10:14:03
* @HXing xu
* sk-redis
* 
**/

public class RedisClient {
	private Socket socket;
	private OutputStream writer;
	private InputStream reader;
	
	


	public RedisClient(String host, int port) throws UnknownHostException, IOException {
		// redis 连接
		socket = new Socket(host, port);
		// 往外写数据
		writer = socket.getOutputStream();
		// 读取
		reader = socket.getInputStream();
		
	}

	public String  get(String key) throws IOException {
		StringBuffer command = new StringBuffer();
		command.append("*2").append("\r\n");
		command.append("$3").append("\r\n");
		command.append("GET").append("\r\n");
		command.append("$").append(key.getBytes().length).append("\r\n");
		command.append(key).append("\r\n");
		return exceConmmand(command);
	}
	
	public String set(String key, String value) throws IOException {
		StringBuffer command = new StringBuffer();
		command.append("*3").append("\r\n");
		command.append("$3").append("\r\n");
		command.append("SET").append("\r\n");
		command.append("$").append(key.getBytes().length).append("\r\n");
		command.append(key).append("\r\n");
		command.append("$").append(value.getBytes().length).append("\r\n");
		command.append(value).append("\r\n");
		
		return exceConmmand(command);
	}	
	
	private String exceConmmand(StringBuffer command) throws IOException {
		
		//发送命令，获取响应
		//怎么发送？
		writer.write(command.toString().getBytes());
		//怎么读取？
		byte[] result = new byte[1024];
		reader.read(result);
		
		return new String(result);
	}

}
