package com.cibertec.gateway.util;

import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    public static final String SECRET = "1bcad41c-e0be-4ebc-b481-328de4fe433f";
    public static final String PREFIX_TOKEN = "Bearer ";
    public static final String HEADER_AUTHORIZATION = "Authorization";

}
