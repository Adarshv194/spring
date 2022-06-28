//package io.adarsh.springdatajpaexp.service;
//
//import io.adarsh.springdatajpaexp.DTO.AuthenticateRequestDTO;
//import io.adarsh.springdatajpaexp.DTO.AuthenticateResponseDTO;
//import io.adarsh.springdatajpaexp.model.AppUser;
//import io.adarsh.springdatajpaexp.utils.JWTUtils;
//import org.apache.http.auth.InvalidCredentialsException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//
//import java.util.Objects;
//
//@Service
//public class AuthenticationService1 {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private JWTUtils jwtUtils;
//
//    public AuthenticateResponseDTO authenticate(AuthenticateRequestDTO authenticateRequestDTO) {
//        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
//                new UsernamePasswordAuthenticationToken(authenticateRequestDTO.getUsername(), authenticateRequestDTO.getPassword());
//        Authentication authentication;
//        try {
//            authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
//        } catch (BadCredentialsException  exception) {
//            throw new BadCredentialsException("Bad credentials: ");
//        }
//        String jwtToken = jwtUtils.generateJWTToken((AppUser) authentication.getPrincipal());
//        return new AuthenticateResponseDTO(jwtToken);
//    }
//}
