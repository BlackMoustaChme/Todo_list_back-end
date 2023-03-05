package rest.service.token;
import java.security.Key;

import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
public class TokenValidator {
    private Key key;

    public TokenValidator(Key lKey) {
        key = lKey;
    }
    //приходит токен мы его разбираем на данные для его сборки собираем заново и сравниваем только созданный с пришедшим
    public String validate(String token) throws Exception {

        try {
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return claims.getBody().getSubject();
        } catch (ExpiredJwtException | MalformedJwtException | SignatureException | UnsupportedJwtException | IllegalArgumentException e) {
            throw new Exception("Invalid JWT");
        }
    }
}
