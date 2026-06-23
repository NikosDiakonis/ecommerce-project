package org.ecommerce.resource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.ecommerce.domain.DigitalProductEntity;
import org.ecommerce.domain.PhysicalProductEntity;
import org.ecommerce.domain.ProductEntity;
import org.ecommerce.domain.ProductSortOption;
import org.ecommerce.service.PricingService;
import org.ecommerce.service.ProductService;

@Path("/products")
public class ProductResource {


    @Inject
    private ProductService productService;

    @Inject
    private PricingService pricingService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    @Operation(summary = "Create a new product", description = "Adds a general product to the database.")
    //TODO: Create new DTO for the new productEntity request, do not use the db entity. Check mapstruct library for conversion.
    public Response addProduct(@Valid ProductEntity productEntity) {
        try {
            //TODO: the service should not be void, and the response should have a productEntity with an Id.
            productService.addProduct(productEntity);
            return Response.status(Response.Status.CREATED).entity(productEntity).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(productEntity).build();
        }
        //TODO: do not try catch in every method, use an ExceptionMapper for the generic Exception and create a common error response.
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    @Path("/physical")
    @Operation(summary = "Create a physical product", description = "Adds a physical product with dimensions/weight.")
    public Response addPhysical(@Valid PhysicalProductEntity product) {
        try {
            productService.addProduct(product);
            return Response.status(Response.Status.CREATED).entity(product).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(product).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    @Path("/digital")
    @Operation(summary = "Create a digital product", description = "Adds a digital product with download links.")
    public Response addDigital(@Valid DigitalProductEntity product) {
        try {
            productService.addProduct(product);
            return Response.status(Response.Status.CREATED).entity(product).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(product).build();
        }
    }


    @GET
    @Operation(
            summary = "Get all products",
            description = "Retrieves a list of all available products. Supports optional sorting."
    )
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts(@QueryParam("page") @DefaultValue("0") int page,
                                @QueryParam("size") @DefaultValue("10") int size,
                                @QueryParam("sortBy") String sortBy) {
        ProductSortOption finalSort = ProductSortOption.NAME;
        if (sortBy != null) {
            try {
                finalSort = ProductSortOption.valueOf(sortBy.toUpperCase());
            } catch (IllegalArgumentException e) {

            }
        }

        return Response.status(Response.Status.OK)
                .entity(productService.getAllProducts(page,size,finalSort))
                .build();


        }



    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}/price")
    @Operation(summary = "Calculate product price", description = "Calculates total price based on product ID and quantity.")
    public Response getPrice(@PathParam("id") Long id,@QueryParam("quantity") int quantity) {
        ProductEntity productEntity = productService.findById(id);
        return Response.status(Response.Status.OK).entity(pricingService.calculatePrice(productEntity,quantity)).build();
    }
}
