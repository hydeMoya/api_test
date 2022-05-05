package com.test.api.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.api.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	

}
