//package io.adarsh.springdatajpaexp.config;
//
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.context.ApplicationListener;
//import org.springframework.security.authentication.event.*;
//import org.springframework.security.authentication.jaas.event.JaasAuthenticationFailedEvent;
//import org.springframework.stereotype.Component;
//
//@Component
//public class InterceptAuthenticationEvent1 implements ApplicationListener<AbstractAuthenticationEvent> {
//
//
//    @Override
//    public void onApplicationEvent(AbstractAuthenticationEvent event) {
//        if (event instanceof AuthenticationSuccessEvent) {
//            System.out.println("Authentication gets successful");
//        }
//
//        if (event instanceof AuthenticationFailureBadCredentialsEvent ||
//                event instanceof AuthenticationFailureCredentialsExpiredEvent ||
//                event instanceof AuthenticationFailureDisabledEvent ||
//                event instanceof AuthenticationFailureProviderNotFoundEvent ||
//                event instanceof AuthenticationFailureLockedEvent ) {
//            System.out.println("Authentication gets failed because of: " + event.getClass().getName());
//        }
//    }
//}
