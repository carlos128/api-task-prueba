package com.taks.prueba.segurity;

import static com.taks.prueba.utilitis.Constants.HEADER_STRING;
import static com.taks.prueba.utilitis.Constants.EXPIRATION_TIME;
import static com.taks.prueba.utilitis.Constants.TOKEN_PREFIX;
import static com.taks.prueba.utilitis.Constants.SECRET;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.taks.prueba.entity.UsuarioEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends  UsernamePasswordAuthenticationFilter {

	 private AuthenticationManager authenticationManager;
	    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
	        this.authenticationManager = authenticationManager;
	    }
	    
	    @Override
	    public Authentication attemptAuthentication(HttpServletRequest req,
	                                                HttpServletResponse res) throws AuthenticationException {
	        try {
	            UsuarioEntity usuarioEntity = new ObjectMapper()
	                    .readValue(req.getInputStream(), UsuarioEntity.class);
	            return authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(
	                    		usuarioEntity.getEmail(),
	                    		usuarioEntity.getPassword(),
	                            new ArrayList<>())
	            );
	        } catch (IOException e) {
	            throw new RuntimeException(e);
	        }
	    }
	    
	    @Override
	    protected void successfulAuthentication(HttpServletRequest req,
	                                            HttpServletResponse res,
	                                            FilterChain chain,
	                                            Authentication auth) throws IOException, ServletException {
	        String token = Jwts.builder()
	                .setSubject(((User) auth.getPrincipal()).getUsername())
	                .claim("IdRol", ((User) auth.getPrincipal()).getAuthorities())
	                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
	                .signWith(SignatureAlgorithm.HS512, SECRET)
	                .compact();
	        res.addHeader("Access-Control-Allow-Origin", "*");
	        res.setHeader("Access-Control-Allow-Methods", "POST, GET,  DELETE, PUT");
	        res.setHeader("Access-Control-Allow-Credentials", "true");
	        res.setHeader("Access-Control-Max-Age", "3600");
	        res.setHeader("Access-Control-Allow-Headers", "Accept, Content-Type, Origin, Authorization, X-Auth-Token");
	        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
	    }

}
