package rest.interceptor;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;
import rest.service.token.Token;

import java.io.IOException;


@Provider
@TokenRequired
public class Interceptor implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
//        String login = requestContext.getHeaderString("Login");
        String token = requestContext.getHeaderString("Token");
        requestContext.setProperty("id", Token.checkToken(token));

    }
}

