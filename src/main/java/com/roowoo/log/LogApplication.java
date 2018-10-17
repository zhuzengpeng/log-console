package com.roowoo.log;

import com.roowoo.log.modules.sys.service.SystemService;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Spring boot启动类
 * @author zzp
 */
@EnableCaching
@SpringBootApplication
@ServletComponentScan("com.roowoo.log")
@ComponentScan(value = "com.roowoo.log" ,lazyInit = true)
public class LogApplication {
	
    public static void main(String[] args) {
    	SpringApplication.run(LogApplication.class, args);
		SystemService.printKeyLoadMessage();
    }

}
