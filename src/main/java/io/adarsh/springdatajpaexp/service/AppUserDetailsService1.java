//package io.adarsh.springdatajpaexp.service;
//
//import io.netty.util.internal.StringUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Objects;
//
//@Service
//public class AppUserDetailsService1 implements UserDetailsService {
//
//    @Autowired
//    private UserService userService;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        if (!StringUtil.isNullOrEmpty(username)) {
//            UserDetails userDetail = userService.loadUserByUsername(username);
//            userDetail.getAuthorities().forEach(grantedAuthority -> System.out.println("Authority associated: " + grantedAuthority.getAuthority()));
//            if (Objects.nonNull(userDetail)) {
//                System.out.println("user with username:" + userDetail.getUsername());
//                System.out.println("user with password:" + userDetail.getPassword());
//                return userDetail;
//            } else {
//                throw new UsernameNotFoundException("Invalid username");
//            }
//        } else {
//            throw new UsernameNotFoundException("Username can not be blank");
//        }
//    }
//}
