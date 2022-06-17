package com.test.api.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.test.api.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
    @Transactional(readOnly = true)
    @Query("SELECT u.email FROM Usuario u WHERE u.email LIKE %?1%")
	public String findByEmail(String email);

}
