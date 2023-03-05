package rest.service.token;

import jakarta.inject.Inject;
import rest.builder.Built;
import rest.model.api.dto.User;
import rest.model.api.in.IUser;

import java.security.Key;
import java.util.Objects;

public class Token {

//    @Inject
//    @Built
//    IUser userModel;
//    private String token;

//    public Token(String token) {
//        this.token = token;
//    }

    public static boolean checkToken(String login, String token) {
        return Objects.equals(login, token);
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
