import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import tester.TestingService;

import java.io.IOException;


@ComponentScan(basePackages = "configuration")
@Configuration
public class Main {
    public static void main(String[] args)  {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
//        context.refresh();
        TestingService testingService = context.getBean(TestingService.class);
        testingService.startTest();
    }
}
