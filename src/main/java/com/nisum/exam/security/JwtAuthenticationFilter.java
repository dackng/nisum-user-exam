package com.nisum.exam.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.filter.OncePerRequestFilter;

import com.nisum.exam.util.SecurityConstants;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    
    private static final String DEFAULT_USERNAME = "default_username";
    private static final String DEFAULT_PASSWORD = "default_password";
  
    private final JwtUtils jwtUtils;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) 
    		throws ServletException, IOException {
    	
    	String token = getToken(request);
        
        if (token != null && jwtUtils.isTokenValid(token)) {
        	List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
            grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
            
            User defaultUserDetails = new User(DEFAULT_USERNAME, DEFAULT_PASSWORD, grantedAuthorityList);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(defaultUserDetails, null,
                    defaultUserDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
    
    private String getToken(HttpServletRequest request) {
    	String header;
    	String token = null;
    	try {
    		 header = request.getHeader(SecurityConstants.AUTHORIZATION_HEADER);
    		 if (header != null && header.startsWith(SecurityConstants.BEARER_PREFIX + " ")) {
    				return header.substring(7);
    		 }
    	     return token;
    	 } catch (NullPointerException e) {
             
             return token;
         }
    }
    
}
