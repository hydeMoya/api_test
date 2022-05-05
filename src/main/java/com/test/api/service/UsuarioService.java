package com.test.api.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.test.api.model.dao.TelefonoRepository;
import com.test.api.model.dao.UsuarioRepository;
import com.test.api.model.dto.UsuarioRequest;
import com.test.api.model.entity.Telefono;
import com.test.api.model.entity.Usuario;

@Service
public class UsuarioService {
	
	@Autowired
    private UsuarioRepository  usuarioRepository;
    @Autowired
    private TelefonoRepository telefonoRepository;
    
    Usuario usuario = new Usuario();
    Telefono telefono = new Telefono();
    
    /*public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }*/
    public Usuario addUsuario(UsuarioRequest req,HttpServletRequest request) {
    	
    	Date fechaCreacion = fechaActual();	
    	List<Telefono> listPhono = new ArrayList<>();
    	
    	String token = request.getHeader(HttpHeaders.AUTHORIZATION);
    	
    	//System.out.println("token->"+token);
    	
    	usuario.setNombre(req.getName());
    	usuario.setEmail(req.getEmail());
    	usuario.setPassword(req.getPassword());
    	usuario.setCreated(fechaCreacion);
    	usuario.setModified(fechaCreacion);
    	usuario.setLastLogin(fechaCreacion);
    	usuario.setToken(token);
    	usuario.setActive(true);
    	
    	
    	telefono.setNumero(req.getPhones().get(0).getNumber());
    	telefono.setCodigoCiudad(req.getPhones().get(0).getCitycode());
    	telefono.setCodigoPais(req.getPhones().get(0).getContrycode());
    	
    	listPhono.add(telefono);
    	
    	usuario.setPhones(listPhono);
    
        return usuarioRepository.save(usuario);
    }
    
    public Date fechaActual() {
    	
    	// Creating the LocalDatetime object
    	LocalDate currentLocalDate = LocalDate.now();
    			
    	// Getting system timezone
    	ZoneId systemTimeZone = ZoneId.systemDefault();
    			
    	// converting LocalDateTime to ZonedDateTime with the system timezone
    	ZonedDateTime zonedDateTime = currentLocalDate.atStartOfDay(systemTimeZone);
    			
    	// converting ZonedDateTime to Date using Date.from() and ZonedDateTime.toInstant()
    	Date utilDate = (Date) Date.from(zonedDateTime.toInstant());
    	
    	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		formatter.format(utilDate);
    	
    	return utilDate;
    }

}
