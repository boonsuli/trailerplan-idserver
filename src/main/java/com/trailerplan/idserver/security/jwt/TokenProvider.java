package com.trailerplan.idserver.security.jwt;

import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.trailerplan.idserver.config.AppProperties;
import com.trailerplan.idserver.model.dto.LocalUserDto;

@Service
public class TokenProvider {

	private static final Logger logger = LoggerFactory.getLogger(TokenProvider.class);

	private AppProperties appProperties;

	public TokenProvider(AppProperties appProperties) {
		this.appProperties = appProperties;
	}

	public String createToken(Authentication authentication) {
		LocalUserDto userPrincipal = (LocalUserDto) authentication.getPrincipal();

		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + appProperties.getAuth().getTokenExpirationMsec());

		String tokenSecret = appProperties.getAuth().getTokenSecret();
		Long userId = userPrincipal.getUser().getId();
		String userIdStr = Long.toString(userId);
		JwtBuilder jwtBuilder = Jwts.builder();
		jwtBuilder = jwtBuilder.setSubject(userIdStr);
		jwtBuilder = jwtBuilder.setIssuedAt(new Date());
		jwtBuilder = jwtBuilder.setExpiration(expiryDate);
		jwtBuilder = jwtBuilder.signWith(SignatureAlgorithm.HS256, tokenSecret);

		String compact = jwtBuilder.compact();
		return compact;
	}

	public Long getUserIdFromToken(String token) {
		JwtParser jwtParser = Jwts.parser();

		String tokenSecret = appProperties.getAuth().getTokenSecret();
		jwtParser = jwtParser.setSigningKey(tokenSecret);
		Jws<Claims> jwsClaim =  jwtParser.parseClaimsJws(token);
		Claims claims = jwsClaim.getBody();
		String subject = claims.getSubject();

		return Long.parseLong(subject);
	}

	public boolean validateToken(String authToken) {
		try {
			JwtParser jwtParser = Jwts.parser();
			String tokenSecret = appProperties.getAuth().getTokenSecret();
			jwtParser = jwtParser.setSigningKey(tokenSecret);
			Jws<Claims> jwsClaim =  jwtParser.parseClaimsJws(authToken);
			return true;
		} catch (SignatureException ex) {
			logger.error("Invalid JWT signature");
		} catch (MalformedJwtException ex) {
			logger.error("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			logger.error("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			logger.error("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			logger.error("JWT claims string is empty.");
		}
		return false;
	}
}