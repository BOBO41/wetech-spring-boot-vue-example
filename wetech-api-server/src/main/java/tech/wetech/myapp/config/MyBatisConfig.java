package tech.wetech.myapp.config;

import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;

@Configuration
@MapperScan(basePackages = "tech.wetech.myapp.modules.*.mapper")
public class MyBatisConfig {

}
