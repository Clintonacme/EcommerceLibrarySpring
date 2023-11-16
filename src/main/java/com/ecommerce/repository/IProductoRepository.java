package com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.entity.Producto;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Integer>{

}
