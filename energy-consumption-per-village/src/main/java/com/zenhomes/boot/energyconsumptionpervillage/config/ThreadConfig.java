package com.zenhomes.boot.energyconsumptionpervillage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class ThreadConfig {

    private static final int CORE_POOL_SIZE = 100;
    private static final int MAX_POOL_SiZE  = 100;
    private static final int QUEUE_CAPACITY = 200;

    @Bean("threadPoolTaskExecutor")
    @Primary
    public TaskExecutor threadPoolTaskExecutor() {

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setMaxPoolSize(MAX_POOL_SiZE);
        executor.setQueueCapacity(QUEUE_CAPACITY);
        executor.setWaitForTasksToCompleteOnShutdown(true)  ;
        executor.setThreadNamePrefix("Energy-Consumption-Per-Village-Thread");
        executor.initialize();

        return executor;
    }
}
