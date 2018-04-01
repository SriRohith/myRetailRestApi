package com.target.caseStudy.myRetail.utils;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.target.caseStudy.myRetail.exceptions.ConnectionTimeOutException;


@Component
public class ApiClientService {
	private final RequestConfig requestConfig=RequestConfig.custom()
            .setConnectTimeout(30000)
            .setConnectionRequestTimeout(30000)
            .setSocketTimeout(30000)
            .build();	
	
	
    CloseableHttpClient httpClient=HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
    
    public ClientResponseEntity getClientService(String url){
		HttpGet httpGet=new HttpGet(url);    		
		httpGet.setHeader("Content-type","application/json");
		ClientResponseEntity clientResponseEntity=new ClientResponseEntity();
		int responseStatus;
         try {
			 HttpResponse response=httpClient.execute(httpGet);
			 responseStatus=response.getStatusLine().getStatusCode();	
			 clientResponseEntity.setResponseStatus(responseStatus);
			 if(responseStatus == 200){
				 clientResponseEntity.setResponseObject(new JSONObject(EntityUtils.toString(response.getEntity())));
			 }
			 return clientResponseEntity;			 
		} catch ( IOException | ParseException | JSONException e) {
			 throw new ConnectionTimeOutException("Exception while making myretail api request: "+e.getMessage());
		}finally{			
			httpGet.releaseConnection();
		}
		
    	   
	}
	
}
