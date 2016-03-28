package br.fpu.rest.hello.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.fpu.rest.hello.domain.Product;

public interface ProductRepository 
	extends JpaRepository<Product, Long> {

}
