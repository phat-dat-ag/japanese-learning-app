package com.dat.common.resource;

import com.dat.common.dto.ApiResponse;
import jakarta.ws.rs.core.Response;

public abstract class BaseResource {

    protected <T> ApiResponse<T> ok(T data) {
        return ApiResponse.success(data);
    }

    protected <T> ApiResponse<T> ok(String message, T data) {
        return ApiResponse.success(message, data);
    }

    protected <T> Response created(String message, T data) {
        return Response.status(Response.Status.CREATED)
                .entity(ApiResponse.success(message, data))
                .build();
    }
}