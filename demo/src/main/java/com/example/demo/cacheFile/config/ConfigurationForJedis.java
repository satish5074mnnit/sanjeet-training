//package com.example.demo.cacheFile.config;
//
//import lombok.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import redis.clients.jedis.HostAndPort;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//import redis.clients.jedis.JedisPooled;
//
//import java.time.Duration;
//
//@Configuration
//public class ConfigurationForJedis {
//
////    @Bean("Jedis")
////    public JedisPooled jedisConnection(){
////
////        HostAndPort hostAndPort=new HostAndPort("rediss://clustercfg.oneapp-bff-eu-redis.1kngk8.euc1.cache.amazonaws.com",6379);
////        JedisPooled pool=null;
////        try{
////            pool=new JedisPooled(hostAndPort);
////        } catch (Exception e) {
////            throw e;
////        }
////        return pool;
////    }
//
//
//
//    @Bean
//    public RedisConnectionFactory redisConnectionFactory(JedisClientConfiguration clientConfiguration) {
//
//        RedisStandaloneConfiguration standaloneConfiguration = new RedisStandaloneConfiguration();
//        standaloneConfiguration.setHostName(redisHost);
//        standaloneConfiguration.setPort(redisPort);
//        standaloneConfiguration.setDatabase(redisDatabase);
//        standaloneConfiguration.setPassword(redisPassword);
//
//        return new JedisConnectionFactory(standaloneConfiguration, clientConfiguration);
//    }
//
//    @Bean
//    public JedisClientConfiguration clientConfiguration() {
//
//        JedisClientConfiguration clientConfiguration = JedisClientConfiguration.builder()
//                .connectTimeout(Duration.ofMillis(redisConnectTimeout))
//                .readTimeout(Duration.ofMillis(redisReadTimeout))
//                .usePooling().poolConfig(redisPoolConfig())
//                .build();
//
//        return clientConfiguration;
//    }
//
//    private JedisPoolConfig redisPoolConfig() {
//
//        JedisPoolConfig poolConfig = new JedisPoolConfig();
//        //Minimum connections in the pool
//        poolConfig.setMinIdle(redisPoolMinSize);
//        //Maximum idle connections in the pool
//        poolConfig.setMaxIdle(redisPoolMaxSize);
//        //Maximum total connections in the pool
//        poolConfig.setMaxTotal(redisPoolMaxSize);
//        //Wait when pool is exhausted? Set to true to wait. To validate setMaxWait, it has to be true.
//        poolConfig.setBlockWhenExhausted(true);
//        //Longest time to wait for connection after pool is exhausted. The default value -1 indicates to wait indefinitely.
//        poolConfig.setMaxWaitMillis(redisPoolMaxWaitMillis);
//        //Set to true to enable connectivity test on creating connections. Default: false.
//        poolConfig.setTestOnCreate(false);
//        //Set to true to enable connectivity test on borrowing connections. Default: false. Set to false for heavy-traffic services to reduce overhead.
//        poolConfig.setTestOnBorrow(true);
//        //Set to true to enable connectivity test on returning connections. Default: false. Set to false for heavy-traffic services to reduce overhead.
//        poolConfig.setTestOnReturn(false);
//        //Indicates whether to check for idle connections. If this is set to false, idle connections are not evicted.
//        poolConfig.setTestWhileIdle(true);
//        //Duration after which idle connections are evicted. If the idle duration is greater than this value and the maximum number of idle connections is reached, idle connections are directly evicted.
//        poolConfig.setSoftMinEvictableIdleTimeMillis(redisPoolSoftMinEvictableIdleTimeMillis);
//        //Disable MinEvictableIdleTimeMillis().
//        poolConfig.setMinEvictableIdleTimeMillis(-1);
//        //Interval for checking and evicting idle connections. Default: 60s.
//        poolConfig.setTimeBetweenEvictionRunsMillis(redisPoolBetweenEvictionRunsMillis);
//        return poolConfig;
//    }
//}
