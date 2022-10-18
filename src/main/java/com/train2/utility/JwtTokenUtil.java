package com.train2.utility;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.train2.model.entity.UserAccount;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
@Component
public class JwtTokenUtil {
    private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000; // 24 hour

	private static final String AUTHORITIES_KEY = "auth";
     
    @Value("stargazer")
    private String SECRET_KEY;

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);
     
    public String generateAccessToken(UserAccount user) {
		String authorities = user.getAuthorities().stream().map(authority -> authority.getAuthority()).collect(Collectors.joining(","));
        return Jwts.builder()
                .setSubject(String.format("%s,%s", user.getId(), user.getUsername()))
				.claim(AUTHORITIES_KEY, authorities)
                .setIssuer("self")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    
    }
    public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
	}

	public Authentication getAuthentication(String token) {

        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        Collection<? extends GrantedAuthority> authorities = Arrays.asList(claims.get(AUTHORITIES_KEY).toString().split(",")).stream()
                    .map(authority -> new SimpleGrantedAuthority(authority)).collect(Collectors.toList());
        User principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			logger.error("Invalid JWT signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			logger.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty: {}", e.getMessage());
		}

		return false;
	}
}