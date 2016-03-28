package br.fpu.rest.hello.service;

import java.util.Collection;

import br.fpu.rest.hello.domain.Product;

public interface ProductService {
	public Product findProduct(Long id);
	public Collection<Product> getAll();
}
