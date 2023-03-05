package rest.service.token;

import java.security.SecureRandom;
import java.security.Key;
import javax.crypto.spec.SecretKeySpec;

public class TokenKey {

    private Key key;

    public TokenKey() {
//        SecureRandom sr = new SecureRandom();
        String secretKey;
        secretKey = "jqpwbdyndnmctygspruzoupaulgvboyzfnqqgrvuvoozhuqnowaftfyxztdykozqsugcqjvdaifzggdyxcgmaeeuvwxunvqlbaouuajozvlbtsqobgujguuqcxjxazqktphamhqvxiblsipgpgdsmzecimvlemnmztwsvwmufofzogznpncfdkuexqnamgpwwxgjsqtkheplrejwzvcpgvoynwoqoedxkkdvedzgcphtbmbjylwpqgndswzqxctg";
        byte[] keyBytes = secretKey.getBytes();
//        secretKey.nextBytes(keyBytes);
        key = new SecretKeySpec(keyBytes,"HmacSHA256");
    }

    public Key getKey() {
        return key;
    }
}
