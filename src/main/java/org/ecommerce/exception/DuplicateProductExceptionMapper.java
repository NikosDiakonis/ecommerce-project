package org.ecommerce.exception;

import jakarta.transaction.RollbackException;
import org.hibernate.exception.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class DuplicateProductExceptionMapper implements ExceptionMapper<RollbackException> {

    @Override
    public Response toResponse(RollbackException e) {
        Throwable current = e;
        while (current.getCause() != null && !(current instanceof java.sql.SQLException)) {
            current = current.getCause();
        }
        if (current instanceof ConstraintViolationException) {
            ProblemDetail problemDetail = new ProblemDetail("about:blank", "Bad Request", 400,current.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(problemDetail).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
    }
