package com.test.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.api.model.dao.UsuarioRepository;
import com.test.api.model.dto.UsuarioRequest;
import com.test.api.model.dto.UsuarioResponse;
import com.test.api.model.entity.Usuario;
import com.test.api.service.UsuarioService;

@RestController
@RequestMapping("/api")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@RequestMapping(value = "/save_usuario", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> saveUsuario(@RequestBody UsuarioRequest req, HttpServletRequest request){
		
	
		Usuario user = usuarioService.addUsuario(req, request);
		
		if(user != null) {
			
			UsuarioResponse response = new UsuarioResponse();
			response.setId(user.getId());
			response.setCreated(user.getCreated());
			response.setModified(user.getModified());
			response.setLast_login(user.getLastLogin());
			response.setToken(user.getToken());
			response.setIsactive(user.isActive());
			
			
			return new ResponseEntity<UsuarioResponse>(response,HttpStatus.CREATED);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);

	}
}
