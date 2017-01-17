package com.fufang.utils;

import redis.clients.jedis.Jedis;

public class RedisUtils {

	public Jedis redisConn(){
		Jedis jedis = null;

		try{
			jedis = new Jedis("172.16.88.114", 6379);
			jedis.auth("111111");
			if(jedis.ping() == "PONG"){
				System.out.println("redis连接成功");
			}
			jedis.select(8);
		}catch(Exception e){
			e.printStackTrace();			
		}
		return jedis;
	}

/*	public static void main(String[] args){
		RedisUtils rc = new RedisUtils();
		Jedis jedis = rc.redisConn();
		jedis.hdel("MATERIAL_MATCODE_SUPPLIERID", "YP7777|66");
		jedis.hdel("MATERIAL_MATCODE_SUPPLIERID", "YP7777|66");	
		System.out.println(jedis.hgetAll("MATERIAL_MATCODE_SUPPLIERID"));
		System.out.println(jedis.hgetAll("PRISTOR_MATCODE_SUPPLIERID"));
	}*/
}



