//package io.adarsh.springdatajpaexp.controller;
//
//import io.adarsh.springdatajpaexp.DTO.AuthenticateRequestDTO;
//
////import io.adarsh.springdatajpaexp.service.AuthenticationService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//public class HomeController {
//
//    @Autowired
//    private AuthenticationService authenticationService;
//
//    @GetMapping("/")
//    public String home() {
//        return ("<h1> Welcome to homepage </h1>");
//    }
//
//    @GetMapping("/user")
//    public String user() {
//        return ("<h1> Welcome User </h1>");
//    }
//
//    @GetMapping("/admin")
//    public String admin() {
//        return ("<h1> Welcome Admin </h1>");
//    }
//
//    @PostMapping("/authenticate")
//    public ResponseEntity<?> authenticate(@RequestBody AuthenticateRequestDTO authenticateRequestDTO) {
//        return ResponseEntity.ok(authenticationService.authenticate(authenticateRequestDTO));
//    }
//
////    @PostMapping("/authenticate")
////    public ResponseEntity<?> authenticate(@RequestBody AuthenticateRequestDTO authenticateRequestDTO) {
////        return ResponseEntity.ok(authenticationService.authenticate(authenticateRequestDTO));
////    }
//
//}
