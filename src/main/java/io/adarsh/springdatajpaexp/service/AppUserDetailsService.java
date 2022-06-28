//package io.adarsh.springdatajpaexp.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Objects;
//
//@Service
//public class AppUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UserService userService;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        if (!Objects.isNull(username)) {
//            UserDetails userDetails = userService.loadUserByUsername(username);
//            if (!Objects.isNull(userDetails))
//                    return userDetails;
//            else
//                throw new UsernameNotFoundException("No user found with the username: " + username);
//        } else {
//            throw new UsernameNotFoundException("Username can not be null");
//        }
//    }
//}
