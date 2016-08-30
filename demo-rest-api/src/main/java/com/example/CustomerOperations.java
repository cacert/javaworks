package com.example;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import io.swagger.annotations.Api;

@WebService
@Path("/customerOperations")
@Api(value = "/pet", description = "Operations about pets")
public interface CustomerOperations {
    @GET
    @Path("/listCustomers/{id}")
	public Customer listCustomers(@PathParam("id") @WebParam(name = "id") long id);
    
    @POST
    @Path("/addCustomer/")
	public Customer addCustomer(Customer c);
}
