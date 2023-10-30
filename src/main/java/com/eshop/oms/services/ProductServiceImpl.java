package com.eshop.oms.services;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.oms.dao.ProductRepository;
import com.eshop.oms.domain.EditProductRequest;
import com.eshop.oms.domain.ProductDTO;
import com.eshop.oms.domain.ProductRequest;
import com.eshop.oms.models.Product;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;
	
	@Override
	public ProductDTO addProduct(ProductRequest productRequest) {
		productRepository.saveAndFlush(Product.builder()
				.description(productRequest.getDescription())
				.partNumber(productRequest.getPartNumber())
				.price(productRequest.getPrice())
				.stockQuantity(productRequest.getStock())
				.productName(productRequest.getProductName())
				.build());
		return null;
	}

	@Override
	public void removeProductByProductId(String productId) {
		try {
			Optional<Product> product = productRepository.findById(Integer.valueOf(productId));
			if(product.isEmpty()) throw new EntityNotFoundException("Product not found in DB");
			else productRepository.deleteById(Integer.valueOf(productId));
		}catch (EntityNotFoundException e) {
			throw new EntityNotFoundException("Product not found in DB");
		}
	}

	@Override
	public void removeProductByPartNumber(String partNumber) {
		try {
			Optional<Product> product = productRepository.findByPartNumber(partNumber);
			if(product.isEmpty()) throw new EntityNotFoundException("Product not found in DB");
			productRepository.deleteById(product.get().getProductId());
		}catch (EntityNotFoundException e) {
			throw new EntityNotFoundException("Product not found in DB");
		}
		
	}

	@Override
	public ProductDTO editProductByProductId(String productId, EditProductRequest editProductRequest) {
		Optional<Product> product = productRepository.findById(Integer.valueOf(productId));
		return generateProductResponse(editProductRequest, product);
	}

	@Override
	public ProductDTO editProductByPartNumber(String partNumber, EditProductRequest editProductRequest) {
		Optional<Product> product = productRepository.findByPartNumber(partNumber);
		return generateProductResponse(editProductRequest, product);
	}

	@Override
	public ProductDTO getProductById(String productId) {
		Optional<Product> product = productRepository.findById(Integer.valueOf(productId));
		if(product.isEmpty()) throw new EntityNotFoundException("Product not found in DB");
		return ProductDTO.builder()
				.productName(product.get().getProductName())
				.productId(product.get().getProductId())
				.description(product.get().getDescription())
				.price(product.get().getPrice())
				.partNumber(product.get().getPartNumber())
				.stock(product.get().getStockQuantity())
				.build();
	}

	@Override
	public ProductDTO getProductByPartNumber(String partNumber) {
		Optional<Product> product = productRepository.findByPartNumber(partNumber);
		if(product.isEmpty()) throw new EntityNotFoundException("Product not found in DB");
		return ProductDTO.builder()
				.productName(product.get().getProductName())
				.productId(product.get().getProductId())
				.description(product.get().getDescription())
				.price(product.get().getPrice())
				.partNumber(product.get().getPartNumber())
				.stock(product.get().getStockQuantity())
				.build();
	}

	@Override
	public List<ProductDTO> getAllProducts() {
		List<Product> products = productRepository.findAll();
		return products.stream().map(product -> ProductDTO.builder()
				.productName(product.getProductName())
				.productId(product.getProductId())
				.description(product.getDescription())
				.partNumber(product.getPartNumber())
				.stock(product.getStockQuantity())
				.price(product.getPrice())
				.build()).toList();
	}

	private ProductDTO generateProductResponse(EditProductRequest editProductRequest, Optional<Product> product) {
		if(product.isEmpty()) throw new EntityNotFoundException("Product not found in DB");
		Product editedProduct = product.get();
		editedProduct.setDescription(StringUtils.isNotEmpty(editProductRequest.getDescription()) ? editProductRequest.getDescription() : product.get().getDescription());
		editedProduct.setProductName(StringUtils.isNotEmpty(editProductRequest.getProductName()) ? editProductRequest.getProductName() : product.get().getProductName());
		editedProduct.setPrice(editProductRequest.getPrice() != null ? editProductRequest.getPrice() : product.get().getPrice());
		editedProduct.setPartNumber(StringUtils.isNotEmpty(editProductRequest.getPartNumber()) ? editProductRequest.getPartNumber() : product.get().getPartNumber());
		editedProduct.setStockQuantity(editProductRequest.getStock()!= null ? editProductRequest.getStock() : product.get().getStockQuantity());
		editedProduct = productRepository.saveAndFlush(editedProduct);
		return ProductDTO.builder()
				.productName(editedProduct.getProductName())
				.productId(editedProduct.getProductId())
				.description(editedProduct.getDescription())
				.price(editedProduct.getPrice())
				.partNumber(editedProduct.getPartNumber())
				.stock(editedProduct.getStockQuantity())
				.build();
	}
}
