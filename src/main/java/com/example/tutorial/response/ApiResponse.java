package com.example.tutorial.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private boolean error;
    private String status;
    private String message;
    private T data;
    private Pagination pagination;

    public ApiResponse(
            boolean error,
            String status,
            String message,
            T data
    ) {
        this.error = error;
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponse<T> success(T data) {
        return success(false, "OK", "Success", data);
    }

    public static <T> ApiResponse<T> success(
            boolean error,
            String status,
            String message,
            T data
    ) {
        return new ApiResponse<>(
                error,
                status,
                message,
                data
        );
    }

    public static <T> ApiResponse<T> error(T data) {
        return new ApiResponse<>(
                true,
                "Error",
                "Error",
                data
        );
    }

    public static <T> ApiResponse<T> error(
            String status,
            String message
    ) {
        return new ApiResponse<>(
                true,
                status,
                message,
                null
        );
    }

    public static <T> ApiResponse<List<T>> paginate(
            Page<T> page,
            int pageNo
    ) {
        ApiResponse<List<T>> response = new ApiResponse<>(
                false,
                "OK",
                "Success",
                page.getContent()
        );

        response.pagination = new Pagination(
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                pageNo
        );

        return response;
    }
}