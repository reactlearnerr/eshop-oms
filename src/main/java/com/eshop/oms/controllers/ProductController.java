package com.eshop.oms.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eshop.oms.domain.EditProductRequest;
import com.eshop.oms.domain.ProductDTO;
import com.eshop.oms.domain.ProductRequest;
import com.eshop.oms.services.ProductService;

@RestController
@RequestMapping(path = "/products/api/v1")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@PostMapping("")
	public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductRequest productRequest){
		ProductDTO productDTO = productService.addProduct(productRequest);
		return new ResponseEntity<>(productDTO, HttpStatus.CREATED);
	}
	
	@PutMapping("/id/{productId}")
	public ResponseEntity<ProductDTO> editProduct(@PathVariable String productId, @RequestBody EditProductRequest editProductRequest){
		ProductDTO productDTO = productService.editProductByProductId(productId,editProductRequest);
		return new ResponseEntity<>(productDTO, HttpStatus.ACCEPTED);
	}
	@PutMapping("/pn/{partNumber}")
	public ResponseEntity<ProductDTO> editProductByPartNumber(@PathVariable String partNumber, @RequestBody EditProductRequest editProductRequest){
		ProductDTO productDTO = productService.editProductByPartNumber(partNumber,editProductRequest);
		return new ResponseEntity<>(productDTO, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/id/{productId}")
	public ResponseEntity<HttpStatus> removeProductById(@PathVariable String productId){
		productService.removeProductByProductId(productId);
		return ResponseEntity.ok(HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/pn/{partNumber}")
	public ResponseEntity<HttpStatus> removeProductByPartNumber(@PathVariable String partNumber){
		productService.removeProductByPartNumber(partNumber);
		return ResponseEntity.ok(HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/pn/{partNumber}")
	public ResponseEntity<ProductDTO> getProductDetailsByPartNumber(@PathVariable String partNumber){
		ProductDTO productDTO = productService.getProductByPartNumber(partNumber);
		return new ResponseEntity<>(productDTO, HttpStatus.OK);
	}
	
	@GetMapping("/id/{productId}")
	public ResponseEntity<ProductDTO> getProductDetailsByProductId(@PathVariable String productId){
		ProductDTO productDTO = productService.getProductById(productId);
		return new ResponseEntity<>(productDTO, HttpStatus.OK);
	}
	
	@GetMapping("")
	public ResponseEntity<List<ProductDTO>> getAllProductDetails(){
		List<ProductDTO> products = productService.getAllProducts();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}
}
