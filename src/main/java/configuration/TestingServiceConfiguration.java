package configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import tester.TestSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tester.TestingService;
import tester.TestingServiceImpl;

import java.io.IOException;


@Configuration
public class TestingServiceConfiguration {

    @Bean
    public TestingService testingService(TestSource source, MessageSource messageSource) throws IOException {
        return new TestingServiceImpl(source, messageSource);
    }

    @Bean
    public  MessageSource messageSource(){
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("/i18n/bundle");
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }
}
