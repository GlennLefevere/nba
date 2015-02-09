package nba.com.services;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan(basePackageClasses = CreateServiceBeans.class)
@EnableScheduling
public class CreateServiceBeans {

}
