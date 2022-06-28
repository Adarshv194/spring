//package io.adarsh.springdatajpaexp.config;
//
//import io.adarsh.springdatajpaexp.model.Role;
//import io.adarsh.springdatajpaexp.model.User;
//import io.adarsh.springdatajpaexp.repos.RoleRepository;
//import io.adarsh.springdatajpaexp.repos.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//public class Bootstrap implements ApplicationRunner {
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private RoleRepository roleRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        User user1 = new User("adarshv194", getEncryptedPassword("test"), "adarshv194@gmail.com");
//        User user2 = new User("adarshv193", getEncryptedPassword("test"), "adarshv193@gmail.com");
//
//        Role role1 = new Role("ROLE_ADMIN");
//        Role role2 = new Role("ROLE_USER");
//
//        user1.addRole(role1);
//        user1.addRole(role2);
//
//        user2.addRole(role2);
//
//        roleRepository.save(role1);
//        roleRepository.save(role2);
//
//        userRepository.save(user1);
//        userRepository.save(user2);
//    }
//
//    public String getEncryptedPassword(String password) {
//        return passwordEncoder.encode(password);
//    }
//}
