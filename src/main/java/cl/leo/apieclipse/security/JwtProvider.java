package cl.leo.apieclipse.security;

import java.util.Date;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtProvider {
	public String generateToken(Authentication authentication) {
		String username = authentication.getName();
		Date currentTime = new Date();
		Date expirationToken = new Date(currentTime.getTime() + SecurityConstans.JWT_EXPIRATION);
		
		String token = Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(expirationToken)
				.signWith(SignatureAlgorithm.HS512, SecurityConstans.JWT_SIGN)
				.compact();
		return token;
	}
	
	public String getUsernameFromJwt(String token) {
		Claims claims = Jwts.parser()
				.setSigningKey(SecurityConstans.JWT_SIGN)
				.parseClaimsJws(token)
				.getBody();
		return claims.getSubject();
	}
	
	public Boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(SecurityConstans.JWT_SIGN).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			throw new AuthenticationCredentialsNotFoundException("Jwt expired or incorrect");
		}
	}
}
