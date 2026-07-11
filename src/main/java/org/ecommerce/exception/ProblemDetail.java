package org.ecommerce.exception;

public record ProblemDetail(String type, String title, int status, String detail) {}