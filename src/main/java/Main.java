import org.springframework.context.support.ClassPathXmlApplicationContext;
import tester.TestingService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring_context.xml");
        TestingService sourceReader = context.getBean(TestingService.class);
        sourceReader.startTest();
    }
}
