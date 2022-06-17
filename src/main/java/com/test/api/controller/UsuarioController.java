package com.test.api.controller;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.api.model.dto.UsuarioRequest;
import com.test.api.model.dto.UsuarioResponse;
import com.test.api.model.entity.Usuario;
import com.test.api.service.UsuarioService;

@RestController
@RequestMapping("/api")
public class UsuarioController {
	
	private static Pattern emailValido = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$");
	private static Pattern pswNamePtrn = Pattern.compile("(^[A-Z]+[a-z]+[0-9]{2})+(?![\\/]{2})");//Mayuscula, letras minúsculas, y dos numeros y no permite caracteres especiales

	@Autowired
	UsuarioService usuarioService;
	
	@RequestMapping(value = "/save_usuario", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> saveUsuario(@RequestBody UsuarioRequest req, HttpServletRequest request){
		
		HashMap<String,Object> map = new HashMap<String,Object>();
		String email = "";
		email = usuarioService.findByEmail(req.getEmail());

		//Valido password
		boolean result = validatePassword(req.getPassword());
		//Valido email
		boolean resultEmail = validateEmail(req.getEmail());

		if(!resultEmail){

			map.put("mensaje","email no valido");
			return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
		}

		if(!result){
			//System.out.println("paswword no valido ::: "+ result + "::: "+ req.getPassword());
			map.put("mensaje","password no valido");
			return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
		}
		
		if(email == null){
		
		// llamar crear usuario	
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
	}else{
		map.put("mensaje", "El correo ya registrado");
		return new ResponseEntity<>(map, HttpStatus.FORBIDDEN);

	}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);

	}

	//Metodo validar password
	public boolean validatePassword(String userName){
		Matcher mtch = pswNamePtrn.matcher(userName);
		if(mtch.matches()){
			return true;
		}
		return false;
	}

	//Metodo validar email
	public boolean validateEmail(String email){
		Matcher mtch = emailValido.matcher(email);
		if(mtch.matches()){
			return true;
		}
		return false;
	}
}
