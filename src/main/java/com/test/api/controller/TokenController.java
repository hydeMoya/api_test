package com.test.api.controller;

import org.springframework.boot.json.GsonJsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.api.model.JwtTokenResponse;
import com.test.api.model.JwtUser;
import com.test.api.model.Login;
import com.test.api.security.JwtGenerator;



@RestController
@RequestMapping(value = "/token", produces = MediaType.APPLICATION_JSON_VALUE)
public class TokenController {
	
	private  JwtGenerator jwtGenerator;
	
	public TokenController(JwtGenerator jwtGenerator) {
		
		this.jwtGenerator = jwtGenerator;	
	}
	
	@PostMapping()
	public ResponseEntity<JwtTokenResponse> generate(@RequestBody final Login login){
		
		JwtUser jwtUser = new JwtUser();
		
		jwtUser = existUser(login);
		System.out.println("jwtUser : "+ jwtUser);
		
		JwtTokenResponse token = new JwtTokenResponse();
		
		token.setToken(jwtGenerator.generate(jwtUser));
		
		if(jwtUser != null) {
			
			return new ResponseEntity<JwtTokenResponse>(token, HttpStatus.OK);
		}else {
			
			return new ResponseEntity<JwtTokenResponse>(HttpStatus.UNAUTHORIZED);
		}
		
	}
	
	private JwtUser existUser(Login login) {
		
		if(login.getUser().equals("test") && login.getPassword().equals("1234")) {
			
			JwtUser jwtUser = new JwtUser();
			
			jwtUser.setUsername(login.getUser());
			jwtUser.setId(1);
			jwtUser.setRole("Admin");
			
			return jwtUser;
			
		}else {
			return null;
		}
			
	}
	
	

}
