package com.practice;

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

//
//        Role role1 = roleRepository.findByRoleName("ROLE_ADMIN").orElse(null);
//
//        if (role1 == null) {
//            role1 = new Role();
//            role1.setRoleName("ROLE_ADMIN");
//            role1.setRoleId(UUID.randomUUID().toString());
//            roleRepository.save(role1);
//        }
//
//        Role role2 = roleRepository.findByRoleName("ROLE_GUEST").orElse(null);
//
//        if (role2 == null) {
//            role2 = new Role();
//            role2.setRoleName("ROLE_GUEST");
//            role2.setRoleId(UUID.randomUUID().toString());
//            roleRepository.save(role2);
//        }
//
//
//        User user1 = userRepository.findByUserName("ram").orElse(null);
//        if (user1 == null) {
//            user1 = new User();
//            user1.setUserName("ram");
//            user1.setPassword(passwordEncoder.encode("ram123"));
//            user1.setUserId(UUID.randomUUID().toString());
//            user1.setRoles(List.of(role1,role2));
//            userRepository.save(user1);
//        }
//
//        User user2 = userRepository.findByUserName("shyam").orElse(null);
//        if (user2 == null) {
//            user2 = new User();
//            user2.setUserName("shyam");
//            user2.setPassword(passwordEncoder.encode("shyam123"));
//            user2.setUserId(UUID.randomUUID().toString());
//            user2.setRoles(List.of(role2));
//            userRepository.save(user2);
//        }

    }
}
