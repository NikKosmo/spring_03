package configuration;

import csv.CsvSource;
import csv.TestSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestSourceConfiguration {

    @Bean
    public TestSource testSource(){
        return new CsvSource();
    }
}
