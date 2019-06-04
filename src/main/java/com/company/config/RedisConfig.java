package com.company.config;

import com.company.config.redis.RedisMessageListener;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.data.redis.serializer.*;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;
import java.util.*;

@Configuration
@PropertySource("classpath:redis.properties")
@EnableCaching
public class RedisConfig {

    @Value("${pool.maxIdle}")
    private int maxIdle;
    @Value("${pool.maxTotal}")
    private int maxTotal;
    @Value("${pool.maxWaitMillis}")
    private int maxWaitMillis;
    @Value("${connectionFactory.hostName}")
    private String hostName;
    @Value("${connectionFactory.port}")
    private int port;
    @Value("${taskSchedule.poolSize}")
    private int poolSize;
    @Value("${channelTopic.name}")
    private String name;
    @Value("${node.name}")
    private String master;
    @Value("${sentinel.ip}")
    private String sentinelIp;
    @Value("${sentinel.port}")
    private String sentinelPort;
    @Value("${node.password}")
    private String password;
    @Value("${redis.cache.keyPrefix}")
    private String keyPrefix;

    @Bean("poolConfig")
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig poolConfig=new JedisPoolConfig();
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMaxTotal(maxTotal);
        poolConfig.setMaxWaitMillis(maxWaitMillis);
        return poolConfig;
    }

    @Bean
    public StringRedisSerializer stringRedisSerializer(){
        return new StringRedisSerializer();
    }

    @Bean
    public JdkSerializationRedisSerializer jdkSerializationRedisSerializer(){
        return new JdkSerializationRedisSerializer();
    }

    @Bean
    public Jackson2JsonRedisSerializer jackson2JsonRedisSerializer(){
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer=
                new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om=new ObjectMapper();
        om.setVisibility(PropertyAccessor.GETTER,JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        om.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        return jackson2JsonRedisSerializer;
    }

    /**
     * Redis哨兵模式下配置
     * @return
     */
    @Bean
    public RedisSentinelConfiguration redisSentinelConfiguration(){
        RedisSentinelConfiguration redisSentinelConfiguration=
                new RedisSentinelConfiguration();
        //配置Redis主服务名称
        redisSentinelConfiguration.setMaster(master);
        Set<RedisNode>sentinelNodes=new HashSet<>();
        for(int i=0;i<sentinelIp.split(",").length;i++){
            String ip=sentinelIp.split(",")[i];
            int port=Integer.parseInt(sentinelPort.split(",")[i]);
            sentinelNodes.add(new RedisNode(ip,port));
        }
        //配置Redis哨兵节点的IP和Port
        redisSentinelConfiguration.setSentinels(sentinelNodes);
        return redisSentinelConfiguration;
    }
    @Bean("connectionFactory")
    public JedisConnectionFactory jedisConnectionFactory(){
        JedisConnectionFactory connectionFactory=new JedisConnectionFactory(redisSentinelConfiguration(),jedisPoolConfig());
        connectionFactory.setPassword(password);
        return connectionFactory;
    }

    @Bean
    public RedisTemplate redisTemplate(){
        RedisTemplate redisTemplate=new RedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        redisTemplate.setDefaultSerializer(stringRedisSerializer());
        redisTemplate.setKeySerializer(stringRedisSerializer());
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer());
        redisTemplate.setHashKeySerializer(stringRedisSerializer());
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer());
        return redisTemplate;
    }

    @Bean
    public RedisCacheManager redisCacheManager(){
        RedisCacheConfiguration cacheConfiguration=RedisCacheConfiguration.defaultCacheConfig();
        cacheConfiguration=cacheConfiguration.entryTtl(Duration.ofMinutes(30l)).disableCachingNullValues()
                //设置缓存的key前缀
                .computePrefixWith(cacheName->cacheName+keyPrefix)
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(stringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer()));
        return RedisCacheManager.builder(RedisCacheWriter.nonLockingRedisCacheWriter(jedisConnectionFactory()))
                .cacheDefaults(cacheConfiguration).build();

    }

    @Bean
    public RedisMessageListener redisMessageListener(){
        RedisMessageListener messageListener=new RedisMessageListener();
        messageListener.setRedisTemplate(redisTemplate());
        return messageListener;
    }

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler(){
        ThreadPoolTaskScheduler taskScheduler=new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(poolSize);
        return taskScheduler;
    }

    @Bean(value = "topicContainer",destroyMethod = "destroy")
    public RedisMessageListenerContainer redisMessageListenerContainer(){
        RedisMessageListenerContainer messageListenerContainer=new RedisMessageListenerContainer();
        messageListenerContainer.setConnectionFactory(jedisConnectionFactory());
        messageListenerContainer.setTaskExecutor(threadPoolTaskScheduler());
        Map<MessageListener,Collection<? extends Topic>>map=new HashMap<>();
        List<Topic>topicList=new ArrayList<>();
        topicList.add(new ChannelTopic(name));
        map.put(redisMessageListener(),topicList);
        messageListenerContainer.setMessageListeners(map);
        return messageListenerContainer;
    }
}
