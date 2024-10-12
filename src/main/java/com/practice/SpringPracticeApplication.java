package com.practice;

import com.practice.security.Person;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringPracticeApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(SpringPracticeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
		Person person=new Person("rashindrayadav","janakpur","rashindrayadav","9625450512");
    }
}
