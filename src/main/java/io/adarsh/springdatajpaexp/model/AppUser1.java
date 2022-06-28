//package io.adarsh.springdatajpaexp.model;
//
//import lombok.*;
//import lombok.experimental.FieldDefaults;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.List;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE)
//public class AppUser1 implements UserDetails {
//    String username;
//    String password;
//    boolean accountNonExpired;
//    boolean accountNonLocked;
//    boolean credentialsNonExpired;
//    boolean enabled;
//    boolean active;
//    List<UserGrantedAuthority> authorityList;
//
//    public AppUser1(String username, String password, List<UserGrantedAuthority> authorityList) {
//        this.username = username;
//        this.password = password;
//        this.authorityList = authorityList;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorityList;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return username;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//    public boolean isActive() {
//        return true;
//    }
//}
