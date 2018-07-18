package configuration;

import tester.TestSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tester.TestingService;
import tester.TestingServiceImpl;

import java.io.IOException;


@Configuration
public class TestingServiceConfiguration {

    @Bean
    public TestingService testingService(TestSource source) throws IOException {
        return new TestingServiceImpl(source);
    }
}
