package rest.infrastructure.in.service.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

import java.security.Key;

public class Token {

//    @Inject
//    @Built
//    IUser userModel;
//    private String token;

//    public Token(String token) {
//        this.token = token;
//    }

    public static String checkToken(String token) {
        TokenKey tokenKey = new TokenKey();
        Key key = tokenKey.getKey();
        TokenValidator tokenValidator = new TokenValidator(key);
        boolean valid;
        try {
            valid = tokenValidator.validate(token);
        } catch (Exception e) {
            return "Token not valid";
        }
        if (valid){
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
                return claims.getBody().getSubject();
        }
        return "Token not valid";
    }

    public static String generateToken (Integer userId) {

//        User user = userModel.getUser(login);

        TokenKey tokenKey = new TokenKey();
        Key key = tokenKey.getKey();
        TokenIssuer tokenIssuer = new TokenIssuer(key);
        String jwt = tokenIssuer.issueToken(userId);
        return jwt;
    }
}
