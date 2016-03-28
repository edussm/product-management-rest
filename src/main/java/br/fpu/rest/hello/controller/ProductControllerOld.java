package br.fpu.rest.hello.controller;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.fpu.rest.hello.domain.Product;

@RestController
@RequestMapping("/product-old")
public class ProductControllerOld {

	private AtomicLong geradorDeIds = new AtomicLong();
	private Map<Long, Product> products;

	public ProductControllerOld() {
		products = new ConcurrentHashMap<Long, Product>();
		for (int i = 0; i < 4; i++) {
			Long id = geradorDeIds.getAndIncrement();
			products.put(id, new Product(id, "Prod_" + id, 10.0 + id));
		}
	}

	@RequestMapping(value = "/{productId}", method = RequestMethod.GET)
	public ResponseEntity<Product> getProduct(@PathVariable Long productId) {
		if (products.containsKey(productId)) {
			return ResponseEntity.ok(products.get(productId));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Collection<Product>> getProducts() {
		return ResponseEntity.ok(products.values());
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		if (product.getId() != null) {
			return ResponseEntity.badRequest().body(null);
		}
		product.setId(geradorDeIds.getAndIncrement());
		products.put(product.getId(), product);
		return ResponseEntity.ok(product);
	}

	@RequestMapping(value = "/{productId}",
			method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteProduct(
			@PathVariable Long productId) {
		if (products.containsKey(productId)) {
			products.remove(productId);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Void> 
	editProduct(@RequestBody Product product) {
		if (product == null || product.getId() == null || 
				!products.containsKey(product.getId())) {
			return ResponseEntity.notFound().build();
		}
		products.put(product.getId(), product);
		return ResponseEntity.ok().build();
	}
}
