package land.leets.Carrot.global.auth.jwt.dto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.annotation.PostConstruct;
import java.util.Collections;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtProvider {
    @Value("${carrot.jwt.key}") // 환경변수 JWT_KEY
    private String jwtSecret; // JWT 서명 키

    @Value("${carrot.jwt.access.expiration}") // 환경변수 ACCESS_EXP
    private long accessTokenExpirationTime; // 액세스 토큰 유효기간

    private SecretKeySpec key; // JWT 키

    @PostConstruct
    public void init() {
        // jwtSecret 주입된 후 SecretKeySpec 생성하여 초기화함
        this.key = new SecretKeySpec(jwtSecret.getBytes(), SignatureAlgorithm.HS256.getJcaName());
    }

    // 액세스 토큰 생성 메소드
    public String generateAccessToken(String email) { // 이메일 기반으로 jwt 생성함
        final Date now = new Date();
        final Date expiration = new Date(now.getTime() + accessTokenExpirationTime * 1000); // 액세스 토큰 유효기간 밀리초로 변환
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(key)
                .compact();
    }

    // 토큰 검증 메소드
    public void validateToken(String token) { // 토큰 검증
        try {
            parseToken(token);
        } catch (ExpiredJwtException e) {
            log.warn("JwtProvider: Expired token");
            throw e;
        } catch (UnsupportedJwtException e) {
            log.warn("JwtProvider: Unsupported token");
            throw e;
        } catch (MalformedJwtException e) {
            log.warn("JwtProvider: Malformed token");
            throw e;
        } catch (IllegalArgumentException e) {
            log.warn("JwtProvider: IllegalArgumentException");
            throw e;
        }
    }

    private Claims parseToken(String token) {  // Claims(jwt에 저장된 사용자 정보를 포함)를 가져옴
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }

    // 인증 정보를 가져오는 메소드
    public Authentication getAuthentication(String token) {
        Claims claims = parseToken(token);
        String email = claims.getSubject(); // 이메일 추출

        // 이메일로 UsernamePasswordAuthenticationToken 반환하여 인증 정보를 생성함
        return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
    }
}
