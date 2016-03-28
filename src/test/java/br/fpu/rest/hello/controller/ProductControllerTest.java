package br.fpu.rest.hello.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.fpu.rest.hello.domain.Product;
import br.fpu.rest.hello.service.ProductService;
import br.fpu.rest.hello.service.ProductServiceImpl;

public class ProductControllerTest {
	final String ROOT_URL = "/product";
	
	MockMvc mockMvc;
	
	@InjectMocks
	ProductController productController;

	@Mock
	ProductService productService;
	
	Map<Long, Product> products;
	
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders
				.standaloneSetup(productController).build();
		
		products = new HashMap<Long, Product>();
		for (int i = 0; i < 10; i++) {
			products.put(Long.valueOf(i), new Product(Long.valueOf(i), "Prod " + i, 10.0 + i));
		}
	}
	
	@Test
	public void lerTodosProdutos() throws Exception{
		Mockito.when(productService.getAll()).thenReturn(products.values());
		mockMvc.perform(get(ROOT_URL))
			.andExpect(status().isOk())
			.andExpect(content()
					.contentType(MediaType.APPLICATION_JSON_UTF8))
			.andDo(print());
	}
	
	@Test
	public void lerProdutoPorId() throws Exception{
		Mockito.when(productService.findProduct(Long.valueOf(1)))
			.thenReturn(products.get(Long.valueOf(1)));

		mockMvc.perform(get(ROOT_URL+"/1"))
			.andExpect(status().isOk())
			.andExpect(content()
			.contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(
	content().json("{'id':1,'name':'Prod 1','price':11.0}"));
	}
	
	@Test
	public void criarProdutoComId() throws Exception{
		mockMvc.perform(post(ROOT_URL)
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content("{\"id\":10,\"name\":\"Prod_10\",\"price\":110.0}"))
			.andExpect(status().isBadRequest());
	}
	
	@Test
	public void criarProdutoCorretoIdNull() throws Exception{
		mockMvc.perform(post(ROOT_URL)
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content("{\"id\":null,\"name\":\"Prod_10\",\"price\":110.0}"))
			.andExpect(status().isOk());
	}
	
	@Test
	public void criarProdutoCorretoSemId() throws Exception{
		mockMvc.perform(post(ROOT_URL)
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content("{\"name\":\"Prod_10\",\"price\":110.0}"))
			.andExpect(status().isOk())
			.andDo(print());
	}
	
	@Test
	public void atualizaProduto() throws Exception{
		mockMvc.perform(put(ROOT_URL)
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content("{\"id\":1,\"name\":\"Produto 1\",\"price\":110.0}"))
			.andExpect(status().isOk())
			.andDo(print());
	}
	
	@Test
	public void atualizaProdutoInexistente() throws Exception{
		mockMvc.perform(put(ROOT_URL)
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content("{\"id\":100,\"name\":\"Produto 100\",\"price\":110.0}"))
			.andExpect(status().isNotFound())
			.andDo(print());
	}
	
	@Test
	public void apagaProduto() throws Exception{
		mockMvc.perform(delete(ROOT_URL+"/1"))
			.andExpect(status().isOk());
	}
}
