package com.kenzie.capstone.service.dependency;

import com.kenzie.capstone.service.VideoGameService;
import dagger.Component;
import redis.clients.jedis.Jedis;

import javax.inject.Singleton;

/**
 * Declares the dependency roots that Dagger will provide.
 */
@Singleton
@Component(modules = {DaoModule.class, ServiceModule.class, CachingModule.class})
public interface ServiceComponent {
   //LambdaService provideLambdaService();
    VideoGameService provideVideoGameService();

    Jedis provideJedis();
}
