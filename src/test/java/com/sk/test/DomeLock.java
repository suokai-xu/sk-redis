package com.sk.test;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
* 2019年3月14日 下午3:35:39
* @HXing xu
* sk-redis
* 
**/

public class DomeLock {
	 public static void main(String[] args) {  
//	       lock();
	       sys();
	    } 
	 
	 public static void lock() {
		 final Out output = new Out();  
	        new Thread() {  
	            public void run() {  
	                output.output("zhangsan");  
	            };  
	        }.start();        
	        new Thread() {  
	            public void run() {  
	                output.output("lisi");  
	            };  
	        }.start();  
	 }
	 
	 public static void sys() {
		 final Out data = new Out();  
	        for (int i = 0; i < 3; i++) {  
	            new Thread(new Runnable() {  
	                public void run() {  
	                    for (int j = 0; j < 5; j++) {  
	                        data.set(new Random().nextInt(30));  
	                    }  
	                }  
	            }).start();  
	        }         
	        for (int i = 0; i < 3; i++) {  
	            new Thread(new Runnable() {  
	                public void run() {  
	                    for (int j = 0; j < 5; j++) {  
	                        data.get();  
	                    }  
	                }  
	            }).start();  
	        } 
		 
	 }
}

