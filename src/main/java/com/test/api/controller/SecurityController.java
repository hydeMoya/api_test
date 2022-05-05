package com.test.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SecurityController {
	
	
	@GetMapping("/acceso_solo_jwt")
	public ResponseEntity<?> getInformacionBancaria(){
		
		List<String> movimientosBancarios = obtenerUltimosMovimientos();
		
		if(movimientosBancarios != null) {
			
			return new ResponseEntity<>(movimientosBancarios,HttpStatus.OK);
			
		}else {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	private List<String> obtenerUltimosMovimientos(){
		
		List<String> movimientosBancarios = new ArrayList<>();
		movimientosBancarios.add("20");
		movimientosBancarios.add("-20");
		movimientosBancarios.add("4520");
		movimientosBancarios.add("-520");
		
		return movimientosBancarios;
		
	}
	

}
