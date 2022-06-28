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
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
//@Service
//public class JWTUtils { // creating JWT, validating JWT, extracting claims through JWT
//
//    @Value("${jwt.security.key}")
//    private String securityKey;
//
//    public String  extractUsername(String jwtToken) {
//        return extractClaim((claims) -> claims.getSubject(), jwtToken);
//    }
//
//    public Date extractIssuedAt(String jwtToken) {
//        return extractClaim((claims) -> claims.getIssuedAt(), jwtToken);
//    }
//
//    public Date extractExpirationAt(String jwtToken) {
//        return extractClaim((claims) -> claims.getExpiration(), jwtToken);
//    }
//
//    private boolean isTokenExpired(String jwtToken) {
//        return extractExpirationAt(jwtToken).before(new Date(System.currentTimeMillis()));
//    }
//
//    private <T> T extractClaim(Function<Claims, T> claimResolver, String jwtToken) {
//        Claims claims = getAllClaims(jwtToken);
//        return claimResolver.apply(claims);
//    }
//
//    private Claims getAllClaims(String jwtToken) {
//        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(securityKey).parseClaimsJws(jwtToken); // here it's checking header + payload with signing key combination
//        return claimsJws.getBody();
//    }
//
//    public String generateJWTToken(UserDetails userDetails) {
//        String username = userDetails.getUsername();
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("username", username);
//        return createToken(claims, username);
//    }
//
//    private String createToken(Map<String, Object> claims, String subject) {
//        return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(subject)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 10 * 60 * 60 * 10))
//                .signWith(SignatureAlgorithm.HS256, securityKey)
//                .compact();
//    }
//
//    public boolean validateToken(String jwtToken, String username) {
//        String tokenUsername = extractUsername(jwtToken);
//        return !isTokenExpired(jwtToken) && username.equals(tokenUsername) ? true : false;
//    }
//
//}
