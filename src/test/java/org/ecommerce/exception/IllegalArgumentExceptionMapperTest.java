package org.ecommerce.exception;

import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IllegalArgumentExceptionMapperTest {
    @Test
     void testExceptionMapper() {
        IllegalArgumentException exception = new IllegalArgumentException("Duplicate Product");

        IllegalArgumentExceptionMapper exceptionMapper = new IllegalArgumentExceptionMapper();
        Response response = exceptionMapper.toResponse(exception);
        int responseCode = response.getStatus();
        ProblemDetail problemDetail = (ProblemDetail) response.getEntity();
        assertEquals(400, responseCode);

        assertEquals("about:blank", problemDetail.type());
        assertEquals("Bad Request", problemDetail.title());
        assertEquals(400, problemDetail.status());
        assertEquals(exception.getMessage(), problemDetail.detail());

    }
}
