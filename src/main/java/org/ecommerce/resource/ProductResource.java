package org.ecommerce.resource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.ecommerce.domain.DigitalProduct;
import org.ecommerce.domain.PhysicalProduct;
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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    @Path("/physical")

    public Response addPhysical(PhysicalProduct product) {
        try {
            productService.addProduct(product);
            return Response.status(201).entity(product).build();
        } catch (IllegalArgumentException e) {
            return Response.status(400).entity(product).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    @Path("/digital")

    public Response addDigital(DigitalProduct product) {
        try {
            productService.addProduct(product);
            return Response.status(201).entity(product).build();
        } catch (IllegalArgumentException e) {
            return Response.status(400).entity(product).build();
        }
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts(@QueryParam("page") @DefaultValue("0") int page,
                                @QueryParam("size") @DefaultValue("10") int size,
                                @QueryParam("sortBy") String sortBy) {
        String finalSort = "name";

        if("price".equals(sortBy)) {
            finalSort = "price";
        }else if("sku".equals(sortBy)) {
            finalSort = "sku";
        }else if("name".equals(sortBy)) {
            finalSort = "name";
        }
        return Response.status(200).entity(productService.getAllProducts(page,size,finalSort)).build();
    }
}
