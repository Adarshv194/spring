//package io.adarsh.springdatajpaexp.service;
//
//import io.adarsh.springdatajpaexp.model.AppUser1;
//import io.adarsh.springdatajpaexp.model.Role;
//import io.adarsh.springdatajpaexp.model.User;
//import io.adarsh.springdatajpaexp.model.UserGrantedAuthority;
//import io.adarsh.springdatajpaexp.repos.UserRepository;
//import io.netty.util.internal.StringUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.util.CollectionUtils;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//public class UserService1 {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        if (!StringUtil.isNullOrEmpty(username)) {
//            Optional<User> userOptional = userRepository.findByUsername(username);
//            if (userOptional.isPresent()) {
//                User user = userOptional.get();
//                return new AppUser1(user.getUsername(), user.getPassword(), getUserGrantedAuthoritiesByRole(user.getRoles()));
//            } else {
//                return null;
//            }
//        } else {
//            throw new UsernameNotFoundException("Username can not be blank");
//        }
//    }
//
//    public List<UserGrantedAuthority> getUserGrantedAuthoritiesByRole(List<Role> userRoles) {
//        if (!CollectionUtils.isEmpty(userRoles)) {
//            return userRoles.stream()
//                    .map(role -> new UserGrantedAuthority(role.getName()))
//                    .collect(Collectors.toList());
//        } else {
//            throw new UsernameNotFoundException("Roles can not be empty for a user");
//        }
//    }
//}
