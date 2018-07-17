package configuration;

import csv.TestSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tester.TestingService;
import tester.TestingServiceImpl;

@Configuration
public class TestingServiceConfiguration {

    @Bean
    public TestingService testingService(TestSource source){
        return new TestingServiceImpl(source);
    }
}
