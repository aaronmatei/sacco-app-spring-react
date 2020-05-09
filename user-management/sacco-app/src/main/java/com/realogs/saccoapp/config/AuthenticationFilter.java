package com.realogs.saccoapp.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.realogs.saccoapp.model.LoginRequestModel;
import com.realogs.saccoapp.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private Environment env;
    private UserService userService;

    public AuthenticationFilter(UserService userService, Environment env, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.env = env;
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
           LoginRequestModel creds = new ObjectMapper()
                   .readValue(request.getInputStream(),LoginRequestModel.class);
           return getAuthenticationManager().authenticate(
                   new UsernamePasswordAuthenticationToken(
                           creds.getUsername(),
                           creds.getPassword(),
                           new ArrayList<>()
                   )
           );

        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        String userName = ((User)authResult.getPrincipal()).getUsername();
        com.realogs.saccoapp.model.User user = userService.getUserDetailByUsername(userName);
        SecretKey secretKey = Keys.hmacShaKeyFor(env.getProperty("token.secret").getBytes());
        String token = Jwts.builder()
                .setSubject(user.getEmail())
                .claim("authorities",authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+Long.parseLong(env.getProperty("token.expiration_time"))))
                .signWith(secretKey)
                .compact();
        response.addHeader("Bearer",token);
        response.addHeader("userId",Long.toString(user.getId()));


    }
}
