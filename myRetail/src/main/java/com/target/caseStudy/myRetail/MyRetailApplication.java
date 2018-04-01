package com.target.caseStudy.myRetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.target.caseStudy.myRetail.dao.ProductDetailDAO;
import com.target.caseStudy.myRetail.entity.ProductDetailEntity;

@SpringBootApplication
@EnableMongoRepositories("com.target.caseStudy.myRetail.dao")
public class MyRetailApplication implements CommandLineRunner{
	
	@Autowired
	private ProductDetailDAO productDetailDAO;

	public static void main(String[] args) {
		SpringApplication.run(MyRetailApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		productDetailDAO.deleteAll();
		
		productDetailDAO.save(new ProductDetailEntity(13860428, 13.49, "USD"));
		productDetailDAO.save(new ProductDetailEntity(16696652, 199.99, "USD"));
		
		
		System.out.println("After loading");
		for(ProductDetailEntity e : productDetailDAO.findAll()){
			System.out.println(e.toString());
		}
	}
}
