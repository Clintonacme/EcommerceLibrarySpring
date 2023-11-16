package com.ecommerce.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

import com.ecommerce.entity.Usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ordenes")
@Data
public class Orden {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String numero;
	private Date fechaCreacion;
	private Date fechaRecibida;
	private double total;
	
	@ManyToOne
	private Usuario usuario;
	
	@OneToMany(mappedBy = "orden")
	private List<DetalleOrden> detalle;
	
	public Orden() {
		
	}

	public Orden(Integer id, String numero, Date fechaCreacion, Date fechaRecibida, double total) {
		super();
		this.id = id;
		this.numero = numero;
		this.fechaCreacion = fechaCreacion;
		this.fechaRecibida = fechaRecibida;
		this.total = total;
	}

	@Override
	public String toString() {
		return "Orden [id=" + id + ", numero=" + numero + ", fechaCreacion=" + fechaCreacion + ", fechaRecibida="
				+ fechaRecibida + ", total=" + total + "]";
	}
	
	
	
}
