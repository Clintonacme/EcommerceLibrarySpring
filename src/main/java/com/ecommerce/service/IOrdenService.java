package com.ecommerce.service;

import java.util.List;
import java.util.Optional;

import com.ecommerce.entity.Usuario;
import com.ecommerce.entity.Orden;

public interface IOrdenService {
	List<Orden>findAll();
	
	Optional<Orden> findById(Integer id);
	
	Orden save(Orden orden);

	String generarNumeroOrden();
	
	List<Orden> findByUsuario (Usuario usuario); //busca las órdenes por usuario
	
	
	}
