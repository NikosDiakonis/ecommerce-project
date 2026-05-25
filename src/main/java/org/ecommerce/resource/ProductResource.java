package org.ecommerce.resource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.ecommerce.domain.DigitalProduct;
import org.ecommerce.domain.PhysicalProduct;
import org.ecommerce.domain.Product;
import org.ecommerce.service.PricingService;
import org.ecommerce.service.ProductService;

@Path("/products")//TODO: Check RESTfull guidelines, I think that plural is not recommended.
public class ProductResource {

    //TODO: add access modifiers (private)
    @Inject
    ProductService productService;

    @Inject
    PricingService pricingService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    //TODO: Create new DTO for the new product request, do not use the db entity. Check mapstruct library for conversion.
    public Response addProduct(Product product) {
        try {
            //TODO: the service should not be void, and the response should have a product with an Id.
            productService.addProduct(product);
            //TODO: use enums instead of hardcoded numbers
            return Response.status(201).entity(product).build();
        } catch (IllegalArgumentException e) {
            return Response.status(400).entity(product).build();
        }
        //TODO: do not try catch in every method, use an ExceptionMapper for the generic Exception and create a common error response.
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
                                @QueryParam("sortBy") String sortBy) {//TODO: Use enum for the sortby to avoid the ifs
        String finalSort = "name";

        if("price".equals(sortBy)) {
            finalSort = "price";
        }else if("sku".equals(sortBy)) {
            finalSort = "sku";
        }else if("name".equals(sortBy)) {//TODO: This is redundant
            finalSort = "name";
        }
        return Response.status(200).entity(productService.getAllProducts(page,size,finalSort)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}/price")
    public Response getPrice(@PathParam("id") Long id,@QueryParam("quantity") int quantity) {
        //TODO: add the findby to the product service
        Product product = Product.findById(id);
        return Response.status(200).entity(pricingService.calculatePrice(product,quantity)).build();
    }
}
