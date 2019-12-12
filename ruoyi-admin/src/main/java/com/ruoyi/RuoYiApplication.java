package com.ruoyi;

import com.ruoyi.web.controller.user.utils.SnowflakeIdGenerator;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * 启动程序
 * 
 * @author ruoyi
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@MapperScan(basePackages = {
        "com.ruoyi.web.controller.user.dao",
        "com.ruoyi.web.controller.blog.dao",
        "com.ruoyi.web.controller.xiaoyi.dao",
        "com.ruoyi.web.controller.report.dao"
})
public class RuoYiApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(RuoYiApplication.class, args);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>> 营销平台运营端-启动成功 <<<<<<<<<<<<<<<<<<<<");
    }

    @Bean
    public SnowflakeIdGenerator snowflakeIdGenerator(){
        return new SnowflakeIdGenerator(1,1);
    }

}