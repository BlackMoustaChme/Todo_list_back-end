package rest.service.token;
import java.security.Key;
import java.util.Objects;

import io.jsonwebtoken.*;

public class TokenValidator {
    private Key key;

    public TokenValidator(Key lKey) {
        key = lKey;
    }
    //приходит токен мы его разбираем на данные для его сборки собираем заново и сравниваем только созданный с пришедшим
    public boolean validate(String token) throws Exception {

        try {
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            String testToken = Jwts.builder()
                    .setSubject(String.valueOf(claims.getBody().getSubject()))
                    .signWith(key, SignatureAlgorithm.HS256)
                    .setIssuedAt(claims.getBody().getIssuedAt())
                    .setExpiration(claims.getBody().getExpiration())
                    .compact();
            return Objects.equals(testToken, token);
        } catch (ExpiredJwtException | MalformedJwtException | SignatureException | UnsupportedJwtException | IllegalArgumentException e) {
            throw new Exception("Invalid JWT");
        }
    }
}
