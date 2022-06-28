//package io.adarsh.springdatajpaexp.service;
//
//import io.adarsh.springdatajpaexp.DTO.AuthenticateRequestDTO;
//import io.adarsh.springdatajpaexp.DTO.AuthenticateResponseDTO;
//import io.adarsh.springdatajpaexp.utils.JWTUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Objects;
//
//@Service
//public class AuthenticationService {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private JWTUtils jwtUtils;
//
//    public AuthenticateResponseDTO authenticate(AuthenticateRequestDTO authenticateRequestDTO) {
//        if (Objects.nonNull(authenticateRequestDTO)) {
//            if (Objects.nonNull(authenticateRequestDTO.getUsername()) && Objects.nonNull(authenticateRequestDTO.getPassword())) {
//                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
//                        new UsernamePasswordAuthenticationToken(authenticateRequestDTO.getUsername(), authenticateRequestDTO.getPassword());
//                Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
//                UserDetails userDetails = (UserDetails) authenticate.getPrincipal();
//                String jwtToken = jwtUtils.generateJWTToken(userDetails);
//                return new AuthenticateResponseDTO(jwtToken);
//            } else {
//                throw new UsernameNotFoundException("Username or password can not be null");
//            }
//        } else {
//            throw new UsernameNotFoundException("Username or password can not be null");
//        }
//    }
//}
