package com.target.caseStudy.myRetail.service;

import org.springframework.http.ResponseEntity;

import com.target.caseStudy.myRetail.pojo.ProductDetail;

public interface ProductDetailService {
	
	public ResponseEntity<ProductDetail> getProductDetails(long id);
	
	public ResponseEntity<ProductDetail> updateProductDetails(long id, ProductDetail productDetail);

}
