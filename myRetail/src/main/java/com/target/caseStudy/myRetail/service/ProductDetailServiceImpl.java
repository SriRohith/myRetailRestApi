package com.target.caseStudy.myRetail.service;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
	private String myRetailUrl;// = "https://redsky.target.com/v2/pdp/tcin/13860428?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics";
	
	@Override
	public ResponseEntity<ProductDetail> getProductDetails(String id) {
		Long productId = Long.parseLong(id);
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
		ProductDetailEntity productDetailEntity = productDetailDAO.findOne(productId);
	    productPrice.setPrice(productDetailEntity.getPrice());
	    productPrice.setCurrency_code(productDetailEntity.getCurrency_code());
   	   
	    productDetail.setId(productId);
	    productDetail.setName(productName);
	    productDetail.setCurrent_price(productPrice);
	    
	    return new ResponseEntity<ProductDetail>(productDetail,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ProductDetail> updateProductDetails(String id, ProductDetail productDetail) {
		Long productId = Long.parseLong(id);
		ProductDetailEntity productDetailEntity = productDetailDAO.findOne(productId);
		if(productDetailEntity != null){
			productDetailEntity.setPrice(productDetail.getCurrent_price().getPrice());
			productDetailEntity.setCurrency_code(productDetail.getCurrent_price().getCurrency_code());
			productDetailDAO.save(productDetailEntity);
		}
		return new ResponseEntity<ProductDetail>(productDetail,HttpStatus.OK);
	}

}
