package org.ecommerce.resource;

import io.quarkus.panache.common.Sort;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.ecommerce.domain.Product;
import org.ecommerce.service.ProductService;

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
    public Response getProducts(@QueryParam("page") int page, @QueryParam("size") int size, @QueryParam("sortBy") String sortBy) {
        Sort sort = (sortBy != null) ? Sort.by(sortBy) : Sort.by("name");
        return Response.status(200).entity(productService.getAllProducts(page,size,sortBy)).build();
    }
}
