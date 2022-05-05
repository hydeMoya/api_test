package com.test.api.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Pattern;

@Entity
@Table(name = "Usuario")
public class Usuario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String nombre;
	
	@Email(message = "email invalido")
	@Column(name = "email", unique = true) // unique campo unico en bd ...no puede repetirse
	private String email; // no lleva name por que se llamara igual que el campo en la BD
	
	@Column(name = "password")
	private String password;

	@Temporal(TemporalType.DATE)
	@Column(name = "created") //,nullable = false, updatable = false
	@CreatedDate
	private Date created;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "modified") //, nullable = false
	private Date modified;
	
	@Column(name = "last_login")
	@Temporal(TemporalType.DATE)
	private Date lastLogin;
	
	@Column(name = "token")
	private String token;
	
	@Column(name = "is_active")
	private boolean isActive;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true) // , mappedBy = "usuario" cascade es por si se borra un registro en la tabla Usuario se borrara
											// registro en la tabla Telefonos
	@JoinColumn(name = "usuario_id", referencedColumnName = "id") // Une la columna usuario_id de la tabla telefono con
																	// la columna id de la tabla Usuario
	//@JsonIgnore
	private List<Telefono> phones;// Lista que declara 1 a N entre la clase usuario y telefono
	
	public Usuario() {
		
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public List<Telefono> getPhones() {
		return phones;
	}

	public void setPhones(List<Telefono> phones) {
		this.phones = phones;
	}
	
	
}
