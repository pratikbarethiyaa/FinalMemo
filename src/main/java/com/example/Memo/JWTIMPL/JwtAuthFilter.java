package com.example.Memo.JWTIMPL;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.Memo.Service.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter{
	private JwtUtil jwtUtil;
	private UserService service;
	public JwtAuthFilter(@Lazy   JwtUtil util,  @Lazy  UserService service) {
		this.jwtUtil=util;
		this.service=service;
	}


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String authHeaderString=request.getHeader("Authorization");
		String usernameString=null;
		String jwtString=null;

		if(authHeaderString!=null && authHeaderString.startsWith("Bearer ")) {
			jwtString=authHeaderString.substring(7);
			usernameString=jwtUtil.extractUsername(jwtString);
		}
		if(usernameString!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userDetails=this.service.loadUserByUsername(usernameString);
			if(jwtUtil.validateToken(jwtString)) {
				UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);

			}
		}
		filterChain.doFilter(request, response);

	}

}
