package com.target.caseStudy.myRetail.service;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.target.caseStudy.myRetail.dao.ProductDetailDAO;
import com.target.caseStudy.myRetail.entity.ProductDetailEntity;
import com.target.caseStudy.myRetail.exceptions.JsonProcessingException;
import com.target.caseStudy.myRetail.exceptions.ProductNotFoundException;
import com.target.caseStudy.myRetail.pojo.ProductDetail;
import com.target.caseStudy.myRetail.pojo.ProductPrice;
import com.target.caseStudy.myRetail.utils.ClientResponseEntity;
import com.target.caseStudy.myRetail.utils.ApiClientService;

@Service
public class ProductDetailServiceImpl implements ProductDetailService {

	@Autowired
	ProductDetailDAO productDetailDAO;
	
	@Autowired
	ApiClientService myRetailApiClientService;
	
	@Value("${myRetailUrl}")
	private String myRetailUrl;
	
	@Override
	public ResponseEntity<ProductDetail> getProductDetails(long id) {
		String productName = "";
		ProductDetail productDetail = new ProductDetail();
		ProductPrice productPrice = new ProductPrice();
		
		ClientResponseEntity clientResponseEntity = myRetailApiClientService
				.getClientService(myRetailUrl+id);
		if (clientResponseEntity.getResponseStatus() != 200) {
			throw new ProductNotFoundException("Product not found with id: "+id);
		}

		JSONObject jsonObject = clientResponseEntity.getResponseObject();
		try {
			if (jsonObject.has("product")) {
				JSONObject productNode = jsonObject.getJSONObject("product");
				if (productNode.has("item")) {
					JSONObject itemNode = productNode.getJSONObject("item");

					if (itemNode.has("product_description")) {
						JSONObject productDescriptionNode = itemNode.getJSONObject("product_description");
						productName = productDescriptionNode.getString("title");
					}
				}
			}
		} catch (JSONException e) {
			throw new JsonProcessingException("Exception while processing Json response object from myRetail Product Api - "+e.getMessage() );
		}
		ProductDetailEntity productDetailEntity = productDetailDAO.findOne(id);
		if(!StringUtils.isEmpty(productDetailEntity)){
	    productPrice.setPrice(productDetailEntity.getPrice());
	    productPrice.setCurrency_code(productDetailEntity.getCurrency_code());
	    
	    productDetail.setId(id);
	    productDetail.setName(productName);
	    productDetail.setCurrent_price(productPrice);
	    return new ResponseEntity<ProductDetail>(productDetail,HttpStatus.OK);
		}else{
			throw new ProductNotFoundException("Product not found with id: "+ id);
		}
	
	}

	@Override
	public ResponseEntity<ProductDetail> updateProductDetails(long id, ProductDetail productDetail) {
		ProductDetailEntity productDetailEntity = productDetailDAO.findOne(id);
		if(!StringUtils.isEmpty(productDetailEntity)){
			productDetailEntity.setPrice(productDetail.getCurrent_price().getPrice());
			productDetailEntity.setCurrency_code(productDetail.getCurrent_price().getCurrency_code());
			if(productDetail.getId()==productDetailEntity.getId())
				productDetailDAO.save(productDetailEntity);
			else
				return new ResponseEntity<ProductDetail>(HttpStatus.BAD_REQUEST);
			return new ResponseEntity<ProductDetail>(productDetail,HttpStatus.OK);
		}else{
			return new ResponseEntity<ProductDetail>(HttpStatus.NOT_FOUND);
		}
	}

}
