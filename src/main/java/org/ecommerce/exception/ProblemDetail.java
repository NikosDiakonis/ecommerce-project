package org.ecommerce.exception;

//Follows RFC 9457 (Problem Details for HTTP APIs) — ex 7807
public record ProblemDetail(String type, String title, int status, String detail) {}