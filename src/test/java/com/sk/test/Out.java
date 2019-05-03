package com.sk.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
* 2019年3月14日 下午3:36:41
* @HXing xu
* sk-redis
* 
**/

public class Out {
	 private Lock lock = new ReentrantLock();// 锁对象  
	  public void output(String name) {  
	      // TODO 线程输出方法  
	      lock.lock();// 得到锁  
	      try {  
	          for(int i = 0; i < name.length(); i++) {  
	              System.out.println(name.charAt(i));  
	          }  
	      } finally {  
	    	 // System.out.println("1-----"+lock.tryLock());
	         lock.unlock();// 释放锁  
	    	  //System.out.println("2-----"+lock.tryLock());
	      }  
	  }  
	  
	  private int data;// 共享数据      
	    public void set(int data) {  
	        System.out.println(Thread.currentThread().getName() + "准备写入数据");  
	        try {  
	            Thread.sleep(20);  
	        } catch (InterruptedException e) {  
	            e.printStackTrace();  
	        }  
	        this.data = data;  
	        System.out.println(Thread.currentThread().getName() + "写入" + this.data);  
	    }     
	    public void get() {  
	        System.out.println(Thread.currentThread().getName() + "准备读取数据");  
	        try {  
	            Thread.sleep(20);  
	        } catch (InterruptedException e) {  
	            e.printStackTrace();  
	        }  
	        System.out.println(Thread.currentThread().getName() + "读取" + this.data);  
	    }  
	    
	    private ReadWriteLock rwl = new ReentrantReadWriteLock();     
	    public void set1(int data) {  
	        rwl.writeLock().lock();// 取到写锁  
	        try {  
	            System.out.println(Thread.currentThread().getName() + "准备写入数据");  
	            try {  
	                Thread.sleep(20);  
	            } catch (InterruptedException e) {  
	                e.printStackTrace();  
	            }  
	            this.data = data;  
	            System.out.println(Thread.currentThread().getName() + "写入" + this.data);  
	        } finally {  
	            rwl.writeLock().unlock();// 释放写锁  
	        }  
	    }     
	    public void get1() {  
	        rwl.readLock().lock();// 取到读锁  
	        try {  
	            System.out.println(Thread.currentThread().getName() + "准备读取数据");  
	            try {  
	                Thread.sleep(20);  
	            } catch (InterruptedException e) {  
	                e.printStackTrace();  
	            }  
	            System.out.println(Thread.currentThread().getName() + "读取" + this.data);  
	        } finally {  
	            rwl.readLock().unlock();// 释放读锁  
	        }  
	    }
}
