package com.target.caseStudy.myRetail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.target.caseStudy.myRetail.controller.ProductDetailController;
import com.target.caseStudy.myRetail.pojo.ProductDetail;
import com.target.caseStudy.myRetail.pojo.ProductPrice;
import com.target.caseStudy.myRetail.service.ProductDetailService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ProductDetailController.class, secure = false)
public class MyRetailApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ProductDetailService productDetailService;


	@Test
	public void getProductDetailsTest() throws Exception {
		ProductPrice productPrice = new ProductPrice(15.89, "USD");
		ProductDetail productDetail = new ProductDetail(13860428, "The Big Lebowski (Blu-ray)", productPrice);

		Mockito.when(productDetailService.getProductDetails(13860428))
				.thenReturn(new ResponseEntity<ProductDetail>(productDetail, HttpStatus.OK));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products/13860428")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result =  mvc.perform(requestBuilder).andReturn();
		String expected = "{id:13860428,name:The Big Lebowski (Blu-ray),current_price:{price:15.89,currency_code:USD}}";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
		
		

	}

}
