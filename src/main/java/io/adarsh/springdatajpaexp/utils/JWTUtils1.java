//package io.adarsh.springdatajpaexp.utils;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jws;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
//@Service
//public class JWTUtils1 {
//
//    @Value("${jwt.security.key}")
//    private String securityKey;
//
//    public String extractUsername(String token) {
//        return extractClaim(token, (claims) -> claims.getSubject());
//    }
//
//    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//        Claims claims = extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//
//    private Claims extractAllClaims(String token) {
//        System.out.println("Security Key is: " +  securityKey);
//        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(securityKey).parseClaimsJws(token); // here it's checking the jwt with header,payload + signature check
//        Claims body = claimsJws.getBody();
//        return body;
//    }
//
//    public Date extractExpirationDate(String token) {
//        return extractClaim(token, (claims -> claims.getExpiration()));
//    }
//
//    public boolean isTokenExpired(String token) {
//        return extractExpirationDate(token).before(new Date(System.currentTimeMillis()));
//    }
//
//    public String generateJWTToken(UserDetails userDetails) {
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("username", userDetails.getUsername());
//        return createToken(claims, userDetails.getUsername());
//    }
//
//    private String createToken(Map<String, Object> claims, String subject) {
//        return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(subject)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 2))
//                .signWith(SignatureAlgorithm.HS256, securityKey)
//                .compact();
//    }
//
//    public boolean validateToken(String token, UserDetails userDetails) {
//        final String username = extractUsername(token);
//        return (username.equals(userDetails.getUsername()));
//    }
//}
