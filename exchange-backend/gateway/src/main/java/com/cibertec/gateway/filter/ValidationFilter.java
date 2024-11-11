package com.cibertec.gateway.filter;


import com.cibertec.gateway.util.JwtUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.StreamSupport;

import static com.cibertec.gateway.util.JwtUtil.*;

@Component
public class ValidationFilter extends AbstractGatewayFilterFactory<ValidationFilter.Config> {

    @Autowired
    private JwtUtil jwtUtil;

    public ValidationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            String header = Objects.requireNonNull(exchange.getRequest().getHeaders().get(HEADER_AUTHORIZATION)).toString();
            if (header == null || !header.startsWith(PREFIX_TOKEN)) {
                chain.filter(exchange);
            }
            String token = header.replace(PREFIX_TOKEN, "").replaceAll("[\\[\\]]", "");
            try {
                Claims claims = Jwts.parser()
                        .verifyWith(new SecretKeySpec(SECRET.getBytes(StandardCharsets.UTF_8), "HmacSHA256"))
                        .build()
                        .parseSignedClaims(token)
                        .getPayload();

                String json = claims.get("authorities").toString();
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = null;
                try {
                    jsonNode = objectMapper.readTree(json);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }

                List<String> roles = StreamSupport.stream(jsonNode.spliterator(), false).map(node -> node.get("authority").asText())
                        .toList();

                if ((long) roles.size() == 1 && methods().stream().anyMatch(method -> exchange.getRequest().getMethod().toString().contains(method))) {
                    throw new RuntimeException("Illegal");
                }


            } catch (JwtException e) {
                System.out.println("invalid access...!");

                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

                String responseBody = "{\"message\": \"Unauthorized access to the application\"}";
                return exchange.getResponse().writeWith(
                        Mono.just(exchange.getResponse().bufferFactory().wrap(responseBody.getBytes(StandardCharsets.UTF_8)))
                );
            }
            return chain.filter(exchange);
        });
    }

    private List<String> methods() {
        List<String> methods = new ArrayList<>();
        //methods.add("GET");
        methods.add("PUT");
        methods.add("POST");
        methods.add("DELETE");
        return methods;
    }

    public static class Config {

    }
}
