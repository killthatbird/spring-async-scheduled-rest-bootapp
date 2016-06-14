
package com.ailhanli.spring.example;

import java.util.concurrent.Executor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class Application extends SpringBootServletInitializer implements AsyncConfigurer {

   public static void main(final String[] args) {
      SpringApplication.run(Application.class, args);
   }

   @Override
   protected final SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
      return application.sources(Application.class);
   }

   @Override
   public Executor getAsyncExecutor() {
      ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
      taskExecutor.setMaxPoolSize(10);
      taskExecutor.setThreadNamePrefix("Platform-");
      taskExecutor.initialize();
      return taskExecutor;
   }

   @Override
   public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
      return new SimpleAsyncUncaughtExceptionHandler();
   }
}
