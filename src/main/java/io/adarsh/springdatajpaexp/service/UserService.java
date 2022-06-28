//package io.adarsh.springdatajpaexp.service;
//
//import io.adarsh.springdatajpaexp.model.AppUser;
//import io.adarsh.springdatajpaexp.model.Role;
//import io.adarsh.springdatajpaexp.model.User;
//import io.adarsh.springdatajpaexp.model.UserGrantedAuthority;
//import io.adarsh.springdatajpaexp.repos.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Objects;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//public class UserService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        if (!Objects.isNull(username)) {
//            Optional<User> optionalUser = userRepository.findByUsername(username);
//            if (optionalUser.isPresent()) {
//                User user = optionalUser.get();
//                List<GrantedAuthority> grantedAuthorityList = this.getUserGrantedAuthorityByRoles(user.getRoles());
//                return new AppUser(user.getUsername(), user.getPassword(), grantedAuthorityList);
//            } else {
//                throw new UsernameNotFoundException("No user found with the username: " + username);
//            }
//        } else {
//            throw new UsernameNotFoundException("Username can not be null");
//        }
//    }
//
//    private List<GrantedAuthority> getUserGrantedAuthorityByRoles(List<Role> roleList) {
//        return roleList.stream().map(role -> new UserGrantedAuthority(role.getName())).collect(Collectors.toList());
//    }
//}
