package com.shoppingmall.example.config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.DatatypeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.shoppingmall.example.domain.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
//@Component 어노테이션: @Bean과 마찬가지로 빈을 등록하는 어노테이션이다.
//다만 개발자가 직접 만든 것을 빈으로 등록할때는 @Bean이 아니라 @Component를 사용한다.
public class JwtUtils {

	private static final String jwtSecret = "lcomputerstudyexample"; //시크릿키. 내가 임의로 지정함. 이 시크릿키를 통하여 토큰을 암호화하고 복구화하게됨
	
	private static final int jwtExpirationMs = 8640000; //jwt토큰의 만료기간
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//jwt 생성 메소드
	public String generateJwtToken(Authentication authentication) {
		System.out.println("체크  JwtUtils");
		User user = (User) authentication.getPrincipal();
		//builder 패턴을 이용하여 jwt생성하는 과정
		return Jwts.builder()
				.setSubject((user.getUsername()))
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)) // 여기서 위에서 설정한 만료기간을 주입함.
				.signWith(SignatureAlgorithm.HS512, jwtSecret) // 여기서 위에서 설정한 시크릿키를 주입함.
				.compact();
	}
	
	//토큰을 이용하여 유저 아이디 불러오는 메소드
	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}
	
	//Claim: 권리
	 private static Claims getClaimsFormToken(String token) {
        return (Claims) Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(jwtSecret))
        		.parseClaimsJws(token).getBody();
    }
	 
	 public static String getUserEmailFromToken(String token) {
	        Claims claims = getClaimsFormToken(token);
	        Map<String, Object> map = new HashMap<>(claims);
	        String username = (String) map.get("sub");
	        
	        return username;
	    }
	
	//jwt 유효성 검사 메소드
	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
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
