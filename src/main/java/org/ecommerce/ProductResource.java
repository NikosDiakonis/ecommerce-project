package org.ecommerce;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/products")

public class ProductResource {
    @Inject
    ProductRepository productRepository;
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional

    public Response addProduct(Product product) {
        productRepository.persist(product);
        return Response.status(201).entity(product).build();
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts(){
        return Response.status(200).entity(productRepository.listAll()).build();
    }
}
