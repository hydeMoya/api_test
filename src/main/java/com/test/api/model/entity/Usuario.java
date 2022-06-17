package com.test.api.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;

import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "Usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_usuario")
	private Long id;

	@Column(name = "name")
	private String nombre;
	
	//@Email(message = "email invalido")
	//@Pattern(regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$", message = "Email debe ser valido")
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
	
	//oneToMany significa que 1 usuario puede tener muchos telefonos
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL) //cascadeTypeAll es para eliminar usuario y todos sus telefonos asignados al momento de eliminar
	private List<Telefono> phones = new ArrayList<>();
	
	public Usuario () {
		super();
	}
	
	public Usuario(Long id, String nombre, String email,
			String password, Date created, Date modified, Date lastLogin, String token, boolean isActive,
			List<Telefono> phones) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.password = password;
		this.created = created;
		this.modified = modified;
		this.lastLogin = lastLogin;
		this.token = token;
		this.isActive = isActive;
		this.phones = phones;
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
		for(Telefono telefono : phones){
			telefono.setUsuario(this);
		}
	}

	

	
	
}
