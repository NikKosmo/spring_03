package ru.otus.homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.homework.tester.TestingService;


@SpringBootApplication
public class Main {
    public static void main(String[] args)  {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
        TestingService testingService = context.getBean(TestingService.class);
        testingService.startTest();
    }
}
