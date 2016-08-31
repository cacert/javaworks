package com.example;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import io.swagger.annotations.Api;

@WebService
@Path("/bookstore")
@Consumes("application/json")
@Produces({"application/json","application/xml"})
@Api(value = "/pet", description = "Operations about pets")
public interface BookStore {
    
    @WebMethod
    @GET
    @Path("/{id}")
    @Consumes("*/*")
    Book getBook(@PathParam("id") @WebParam(name = "id") Long id) throws Exception;

    @POST
    @Path("/books")
    @Consumes({"application/json","application/xml"})
    Book addBook(Book book);
   
}
