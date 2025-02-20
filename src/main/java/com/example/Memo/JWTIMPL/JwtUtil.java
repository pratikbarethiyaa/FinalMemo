package com.example.Memo.JWTIMPL;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Value;

@Component
public class JwtUtil {


	private final SecretKey jwtSecret = Keys.secretKeyFor(SignatureAlgorithm.HS512);

	@org.springframework.beans.factory.annotation.Value("${bezkoder.app.jwtExpirationMs}")
	private int jwtExpirationMs;

	public String generateToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
				.signWith(jwtSecret) // Use the SecretKey directly
				.compact();
	}
	public boolean validateToken(String token) {
		try {
			Claims claims = Jwts.parserBuilder()
					.setSigningKey(jwtSecret)
					.build() // Build the parser
					.parseClaimsJws(token) // Parse the JWT
					.getBody();
			return true;
		}
		catch(JwtException | IllegalArgumentException e) {
			return false;
		}
	}
	public String extractUsername(String token) {
		Claims claims = Jwts.parserBuilder()
				.setSigningKey(jwtSecret)
				.build() // Build the parser
				.parseClaimsJws(token) // Parse the JWT
				.getBody();
		return claims.getSubject();
	}

}
