package br.fpu.rest.hello.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private double price;
	
	public Product() {
	}

	public Product(Long id, String name, double price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + "]";
	}
	
}
