package edu.ncsu.csc.itrust2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"edu.ncsu.csc.itrust2"})
public class ITrust2Application {
    public static void main(final String[] args) {
        SpringApplication.run(ITrust2Application.class, args);
    }
}
