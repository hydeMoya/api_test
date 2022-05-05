package com.test.api.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.test.api.model.JwtAuthenticationToken;
import com.test.api.model.JwtUser;
import com.test.api.model.JwtUserDetails;

@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	@Autowired
	JwtValidator validator;
	
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		
		JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken)authentication;
		String token = jwtAuthenticationToken.getToken();
		
		JwtUser jwtUser = validator.validate(token);
		
		if(jwtUser == null) {
			
			throw new RuntimeException("Jwt es incorrecto");
		}
		
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(jwtUser.getRole());
		
		return new JwtUserDetails(jwtUser.getUsername(),token,jwtUser.getId(),grantedAuthorities);
	
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
	}
	
	

}
