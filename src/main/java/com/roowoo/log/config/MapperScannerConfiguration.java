package com.roowoo.log.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.roowoo.log.common.persistence.annotation.MyBatisDao;


/**
 * MyBaits配置类
 * @author	zzp
 */
@Component
public class MapperScannerConfiguration {

	/**
	 * 扫描basePackage下所有以@MyBatisDao注解的接口
	 * @return
	 */
    @Bean(name = "mapperScannerConfigurer")
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.roowoo.log");
        mapperScannerConfigurer.setAnnotationClass(MyBatisDao.class);
        return mapperScannerConfigurer;
    }
    
}
