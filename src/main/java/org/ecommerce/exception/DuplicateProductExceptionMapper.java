package org.ecommerce.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class DuplicateProductExceptionMapper implements ExceptionMapper<IllegalArgumentException> {

    @Override
    public Response toResponse(IllegalArgumentException exception) {
        ProblemDetail problemDetail = new ProblemDetail("about:blank", "Duplicate Resource Product", 400,exception.getMessage());
        return Response.status(Response.Status.BAD_REQUEST).entity(problemDetail).build();
    }
}