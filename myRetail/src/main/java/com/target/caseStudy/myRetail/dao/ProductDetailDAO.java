package com.target.caseStudy.myRetail.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.target.caseStudy.myRetail.entity.ProductDetailEntity;

@Repository
public interface ProductDetailDAO extends MongoRepository<ProductDetailEntity, Long>{

}
