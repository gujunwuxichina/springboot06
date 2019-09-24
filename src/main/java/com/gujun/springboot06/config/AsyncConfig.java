package com.gujun.springboot06.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync    //开启异步可用,这样就可以使用@Async来驱动Spring使用异步了；
public class AsyncConfig implements AsyncConfigurer {

    //定义线程池
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor=new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(10); //核心线程数
        threadPoolTaskExecutor.setMaxPoolSize(30);
        threadPoolTaskExecutor.setQueueCapacity(2000);  //线程队列最大线程数
        threadPoolTaskExecutor.initialize();    //初始化
        return threadPoolTaskExecutor;
    }

}
