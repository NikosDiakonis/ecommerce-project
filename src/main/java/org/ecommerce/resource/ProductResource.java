package org.ecommerce.resource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.ecommerce.service.ProductService;
import org.ecommerce.domain.Product;

@Path("/products")

public class ProductResource {

    @Inject
    ProductService productService;
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional

    public Response addProduct(Product product) {
        try {
            productService.addProduct(product);
            return Response.status(201).entity(product).build();

        } catch (IllegalArgumentException e) {
            return Response.status(400).entity(product).build();
        }
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts(){
        return Response.status(200).entity(productService.getAllProducts()).build();
    }
}
