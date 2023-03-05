package rest.service.token;

import java.security.Key;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class TokenIssuer {
    private Key key;

    public TokenIssuer(Key lKey) {
        key = lKey;
    }

    public String issueToken(Integer userId) {

        LocalDateTime expiryPeriod = LocalDateTime.now().plusMinutes(600L);
        Date expirationDateTime = Date.from(
                expiryPeriod.atZone(ZoneId.systemDefault())
                        .toInstant());


        String compactJws = Jwts.builder()
                .setSubject(String.valueOf(userId))
//                .claim("scope", "user")//Полезная нагрузка токена, какие-либо ёще нужные параметры
                .signWith(key, SignatureAlgorithm.HS256)
                .setIssuedAt(new Date())
                .setExpiration(expirationDateTime)
                .compact();

        return compactJws;
    }
}
