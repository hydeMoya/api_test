package com.test.api.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "Telefono")
public class Telefono implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_telefono")
	private Long telefono_id;
	
	@Column(name = "number")
	private String numero;
	
	@Column(name = "citycode")
	private String codigoCiudad;
	
	@Column(name = "contrycode")
	private String codigoPais;

	
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario") //indica cual es propietaria
	@JsonProperty(access = Access.WRITE_ONLY)// para evitar lazy initializacion Exception 
	private Usuario usuario;
	
	public Telefono(){
		
	}
	
	public Telefono(Long telefono_id, String numero, String codigoCiudad, String codigoPais) {
		super();
		this.telefono_id = telefono_id;
		this.numero = numero;
		this.codigoCiudad = codigoCiudad;
		this.codigoPais = codigoPais;
	}

	public Long getTelefono_id() {
		return telefono_id;
	}

	public void setTelefono_id(Long telefono_id) {
		this.telefono_id = telefono_id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCodigoCiudad() {
		return codigoCiudad;
	}

	public void setCodigoCiudad(String codigoCiudad) {
		this.codigoCiudad = codigoCiudad;
	}

	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	

}
