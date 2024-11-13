package com.beysa.services.UserDomain.Security;

import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;

import io.jsonwebtoken.security.Keys;

public class TokenJwtConfig {    
    public static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor("kq1hmzglh3ucvea5fz7948xofux7f6538y".getBytes(StandardCharsets.UTF_8));
    public static final String PREFIX_TOKEN = "Bearer ";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String CONTENT_TYPE = "application/json";
}
