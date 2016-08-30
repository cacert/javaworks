package com.example;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.cxf.jaxrs.CxfRsEndpoint;
import org.apache.cxf.jaxrs.swagger.Swagger2Feature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestRoute extends RouteBuilder{

	//curl -H "Content-Type: application/json" -H "accept: application/json" -X POST -d '{"name":"xyz","isbn":"xyz","price":"110.00"}' http://localhost:8080/api/servlet/rest/bookstore/books
	//{"name":"name","isbn":"kasim","price":10.0}%
	
	@Autowired
	private Swagger2Feature swagger2Feature;
	
	@Autowired
	private CamelContext context;
	
	private static final String SOAP_ENDPOINT_URI = "cxf:///servlet/soap1"
		        + "?serviceClass=com.example.BookStore";

	private static final String REST_ENDPOINT_URI = "cxfrs:///servlet/rest"
		        + "?resourceClasses=com.example.BookStore,com.example.CustomerOperations&providers=#jsonProvider";
	
	private static final String SOAP_ENDPOINT_URI_CUSTOMER = "cxf:///servlet/soap2"
	        + "?serviceClass=com.example.CustomerOperations";

	
		  
	@Override
	public void configure() throws Exception {
		System.err.println(swagger2Feature);
		CxfRsEndpoint endpoint = (CxfRsEndpoint)context.getEndpoint(REST_ENDPOINT_URI);
		
		endpoint.getFeatures().add(swagger2Feature);
		
		from(SOAP_ENDPOINT_URI)
			.log("came to soap");
		
		from(endpoint)
		.log("${headers}")
		//.log("${body}")
		.process(new Processor() {
			
			@Override
			public void process(Exchange exchange) throws Exception {
//				Customer c = new Customer();
//				c.setBirthDate(Calendar.getInstance().getTime());
//				c.setCustomerName("kasim sert");
//				exchange.getIn().setBody(c);
				Book b = new Book();
				b.setIsbn("kasim");
				b.setName("name");
				b.setPrice(10.0);
				
				Book b2 = new Book();
				b2.setIsbn("eren");
				b2.setName("name");
				b2.setPrice(1000.0);
				
				List<Book> list = new ArrayList<>();
				list.add(b);
				list.add(b2);
				exchange.getIn().setBody(list);
			}
		})	;
//		.marshal()
//		.json(JsonLibrary.Jackson)
//		.log("${body}");
		
		from(SOAP_ENDPOINT_URI_CUSTOMER)
			.log("For customer service call");
		
	}

}
