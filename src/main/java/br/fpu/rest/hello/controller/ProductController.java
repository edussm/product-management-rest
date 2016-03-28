package br.fpu.rest.hello.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.fpu.rest.hello.domain.Product;
import br.fpu.rest.hello.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	private ProductService productService;
	
	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@RequestMapping(value = "/{productId}", method = RequestMethod.GET)
	public ResponseEntity<Product> 
		getProduct(@PathVariable Long productId) {
		Product product = 
				productService.findProduct(productId);
		
		if (product != null) {
			return ResponseEntity.ok(product);
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Collection<Product>> getProducts() {
		return ResponseEntity.ok(productService.getAll());
	}

}
