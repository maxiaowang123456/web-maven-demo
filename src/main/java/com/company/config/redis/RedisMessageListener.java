package com.company.config.redis;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisMessageListener implements MessageListener {

    private RedisTemplate redisTemplate;
    @Override
    public void onMessage(Message message, byte[] bytes) {
        byte[]body=message.getBody();
        String msgBody=(String)redisTemplate.getValueSerializer().deserialize(body);
        System.out.println(msgBody);
        byte[] channel=message.getChannel();
        String msgChannel=(String)redisTemplate.getStringSerializer().deserialize(channel);
        System.out.println(msgChannel);
        String byteStr=new String(bytes);
        System.out.println(byteStr);
    }

    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
