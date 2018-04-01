package com.target.caseStudy.myRetail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.target.caseStudy.myRetail.pojo.ProductDetail;
import com.target.caseStudy.myRetail.service.ProductDetailService;

@RestController
@RequestMapping(value = "/products")
public class ProductDetailController {
	
	@Autowired
	ProductDetailService productDetailService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ProductDetail> getProductDetails(@PathVariable(value = "id") long id) {

		return productDetailService.getProductDetails(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ProductDetail> updateProductDetails(@PathVariable(value = "id") long id,
			@RequestBody ProductDetail productDetail) {
		
		return productDetailService.updateProductDetails(id, productDetail);
	}

}
