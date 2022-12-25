package com.jwt.demo.jwtConfig;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthFilterJwt extends OncePerRequestFilter {
	private static final Logger logger = LoggerFactory.getLogger(AuthFilterJwt.class);

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

//		we got token from request header
		String requestToken = request.getHeader("Authorization");

		String username = null;
		String token = null;

		if (requestToken != null && requestToken.startsWith("Bearer ")) {
			String actualToken = requestToken.substring(7);
			try {
				username = jwtUtil.getUserNameFromToken(actualToken);
			} catch (MalformedJwtException e) {
				logger.error("Invalid JWT token: {}", e.getMessage());
			} catch (ExpiredJwtException e) {
				logger.error("JWT token is expired: {}", e.getMessage());
			} catch (IllegalArgumentException e) {
				logger.error("JWT claims string is empty: {}", e.getMessage());
			} catch (Exception e) {
				logger.error("Error occured while gettin userName from token" + e.getMessage());
			}
		} else {
			logger.error("JWT token doesnot start with Bearer");
		}

//		we find out user name from token, now we need to validate
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			
			
			if(jwtUtil.validateToken(token, userDetails)) {
//				Valid Token , now we need to authenticate
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}else {
				logger.error("Invalid Token");
			}
		}else {
			logger.error("UserName is null or context is not null");
		}

		filterChain.doFilter(request, response);
	}

}
