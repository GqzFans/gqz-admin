package me.gqz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.servlet.MultipartConfigElement;


/**
 * <p>Title: UacTitanApplication. </p>
 * @author dragon
 * @date 2018/3/28 下午10:45
 */
@EnableScheduling
@SpringBootApplication
public class StartApplication {
    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 设置文件大小限制,超了页面会抛出异常信息,这个时候需要进行异常处理
        factory.setMaxFileSize(20 * 1024 * 1024);
        // 设置总上传数据总大小
        factory.setMaxRequestSize(20 * 1024 * 1024);
        return factory.createMultipartConfig();
    }
}
