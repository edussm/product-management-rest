package br.fpu.rest.hello.service;

import java.util.Collection;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.fpu.rest.hello.domain.Product;
import br.fpu.rest.hello.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{

	private ProductRepository repository;
	
	@Autowired
	public ProductServiceImpl(ProductRepository repository) {
		this.repository = repository;
	}

	@Override
	public Product findProduct(@NotNull Long id) {
		return repository.findOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Product> getAll() {
		return repository.findAll();
	}

}
