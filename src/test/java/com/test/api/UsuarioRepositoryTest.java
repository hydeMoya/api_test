package com.test.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.test.api.model.dao.UsuarioRepository;
import com.test.api.model.entity.Telefono;
import com.test.api.model.entity.Usuario;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class UsuarioRepositoryTest {
	
	Date date = new Date(0);
	@Autowired
	UsuarioRepository repo;
	
	@Test
	@Rollback(true)
	void UsuarioSaveTest(){
		List<Telefono> listPhones = new ArrayList<>();
		Telefono phono = new Telefono((long) 1,"423423","23423","23423");
		listPhones.add(phono);
		Usuario usuario = new Usuario((long) 2,"nametest","test@test.cl","12345",date,date,date,"eyrdweddfgttew",true,listPhones);
		Usuario savedUsuario= repo.save(usuario);
		assertNotNull(savedUsuario);
	}	
	
}
