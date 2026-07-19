package org.ecommerce.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

// TODO: add unit test for this mapper — verify:
//   1. status code is 400
//   2. ProblemDetail.type == "about:blank"
//   3. ProblemDetail.title == "Bad Request"
//   4. ProblemDetail.detail == exception.getMessage()
@Provider
public class IllegalArgumentExceptionMapper implements ExceptionMapper<IllegalArgumentException> {

    @Override
    public Response toResponse(IllegalArgumentException exception) {
        ProblemDetail problemDetail = new ProblemDetail("about:blank", "Bad Request", 400,exception.getMessage());
        return Response.status(Response.Status.BAD_REQUEST).entity(problemDetail).build();
    }
}