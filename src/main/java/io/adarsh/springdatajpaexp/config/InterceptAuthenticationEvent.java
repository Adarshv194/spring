//package io.adarsh.springdatajpaexp.config;
//
//import org.springframework.context.ApplicationListener;
//import org.springframework.security.authentication.event.*;
//import org.springframework.stereotype.Component;
//
//@Component
//public class InterceptAuthenticationEvent implements ApplicationListener<AbstractAuthenticationEvent> {
//
//    @Override
//    public void onApplicationEvent(AbstractAuthenticationEvent event) {
//        if (event instanceof AuthenticationSuccessEvent) {
//            System.out.println("Authentication gets successful");
//        }
//        if (event instanceof AuthenticationFailureBadCredentialsEvent ||
//                event instanceof AuthenticationFailureProviderNotFoundEvent ||
//                event instanceof AuthenticationFailureDisabledEvent ||
//                event instanceof AuthenticationFailureCredentialsExpiredEvent ||
//                event instanceof AuthenticationFailureLockedEvent) {
//            System.out.println("Authentication failed: " + event.getClass().getName());
//        }
//    }
//}
