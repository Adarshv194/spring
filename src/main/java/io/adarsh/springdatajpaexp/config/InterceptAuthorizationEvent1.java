//package io.adarsh.springdatajpaexp.config;
//
//import org.springframework.context.ApplicationListener;
//import org.springframework.security.access.event.AbstractAuthorizationEvent;
//import org.springframework.security.access.event.AuthorizationFailureEvent;
//import org.springframework.security.access.event.AuthorizedEvent;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//
//@Component
//public class InterceptAuthorizationEvent1 implements ApplicationListener<AbstractAuthorizationEvent> {
//
//    @Override
//    public void onApplicationEvent(AbstractAuthorizationEvent event) {
//        if (event instanceof AuthorizedEvent) {
//            System.out.println("Authorization gets successful");
//        }
//
//        if (event instanceof AuthorizationFailureEvent) {
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            authentication.getAuthorities().forEach(grantedAuthority -> System.out.println("in event: " + grantedAuthority.getAuthority()));
//            System.out.println("Authorization gets failed: " + event.getClass().getName() + ((AuthorizationFailureEvent) event).getAccessDeniedException());
//        }
//    }
//}
