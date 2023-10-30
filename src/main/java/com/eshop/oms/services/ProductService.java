package com.eshop.oms.services;

import java.util.List;

import com.eshop.oms.domain.EditProductRequest;
import com.eshop.oms.domain.ProductDTO;
import com.eshop.oms.domain.ProductRequest;

public interface ProductService {
	public ProductDTO addProduct(ProductRequest product);
	public void removeProductByProductId(String productId);
	public void removeProductByPartNumber(String partNumber);
	public ProductDTO editProductByProductId(String productId, EditProductRequest editProductRequest);
	public ProductDTO editProductByPartNumber(String partNumber, EditProductRequest editProductRequest);
	public ProductDTO getProductById(String productId);
	public ProductDTO getProductByPartNumber(String partNumber);
	public List<ProductDTO> getAllProducts();
}
