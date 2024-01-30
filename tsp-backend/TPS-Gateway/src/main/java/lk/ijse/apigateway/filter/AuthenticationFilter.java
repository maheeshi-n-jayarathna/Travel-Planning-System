package lk.ijse.apigateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;



@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            System.out.println("awa");

            // check public or not check using endpoint
            // public endpoint have /public segment end of the url
            // localhost:bla bla/package/public - no need security
            if (validateIsSecured(exchange.getRequest())) { // not public segment end of the url

                // check header have AUTHORIZATION header
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    // not have
                    // so can't validate token
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "missing authorization header");
                }
                // have AUTHORIZATION header we move on

                // extract AUTHORIZATION header value
                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);

                // authHeader is null - no token
                // validate authHeader start with Bearer string, so check it
                if (authHeader != null && authHeader.startsWith("Bearer ")) {

                    // have Bearer string so we need remove it
                    // for get token
                    // ex: authHeader = Bearer dhsuihvdijvisvdijvivjvdskdnjcdn
                    authHeader = authHeader.substring(7);
                    // now authHeader without Bearer string
                    // ex: authHeader = dhsuihvdijvisvdijvivjvdskdnjcdn
                }
                try {

                    // we added restTemplate bean on here ../config/GatewayConfig.class
                    // so we can use that bean
                    // for call one service to another service
                    RestTemplate restTemplate = new RestTemplate();

                    // we need call auth service for token validation method
                    // token not valid throw exception
                    // we are on the try catch block , so throw exception it goes catch block
                    // no exception error so not go catch block
                    // watch auth service/api/AuthController.class
                    // me call eka anik service akt yaddi eke fileter ekt thm mulinm yanne
                    // eka balanna authservice/filter/JwtAuthFilter.class
                    restTemplate.getForObject("http://localhost:8082/auth/api/v1/auth/validate?token=" + authHeader, String.class);
                } catch (Exception e) {

                    // we need send response token is not valid
                    throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "un authorized access to application");
                }
            }
            // no exception so came to this code line
            // that is go private end point
            // continue karagena yanawa token eka valid nisa
            return chain.filter(exchange);
        });
    }

    // check endpoint have public segment or not
    // have public return false
    // not have public return true
    private boolean validateIsSecured(ServerHttpRequest request) {
        if (request.getURI().getPath().contains("/public"))
            return false;
        return true;
    }

    public static class Config {

    }
}
