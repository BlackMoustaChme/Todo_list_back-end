package rest.service.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

import java.security.Key;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        try {
            if (tokenValidator.validate(token)){
                Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
                return claims.getBody().getSubject();
            }
            else {
                return "Token not valid";
            }
        } catch (Exception e) {
            Logger.getLogger(Token.class.getName()).log(Level.INFO, null, e);
            throw new RuntimeException(e);
        }
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
