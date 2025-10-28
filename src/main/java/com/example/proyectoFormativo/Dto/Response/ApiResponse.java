package com.example.proyectoFormativo.Dto.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.util.List;

public class ApiResponse<T> {

    private int httpStatusCode;
    private String message;
    private T data;
    private List<String> errors;
    private Object extraData;

    // Paginación
    private Integer totalRecords;
    private Integer pageNumber;
    private Integer pageSize;

    public Integer getTotalPages() {
        if (pageSize != null && pageSize > 0 && totalRecords != null)
            return (int) Math.ceil((double) totalRecords / pageSize);
        return null;
    }

    // --- Constructores ---
    public ApiResponse() {}

    public ApiResponse(int httpStatusCode, String message, T data) {
        this.httpStatusCode = httpStatusCode;
        this.message = message;
        this.data = data;
    }

    // --- Getters & Setters ---
    public int getHttpStatusCode() { return httpStatusCode; }
    public void setHttpStatusCode(int httpStatusCode) { this.httpStatusCode = httpStatusCode; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public T getData() { return data; }
    public void setData(T data) { this.data = data; }

    public List<String> getErrors() { return errors; }
    public void setErrors(List<String> errors) { this.errors = errors; }

    public Object getExtraData() { return extraData; }
    public void setExtraData(Object extraData) { this.extraData = extraData; }

    public Integer getTotalRecords() { return totalRecords; }
    public void setTotalRecords(Integer totalRecords) { this.totalRecords = totalRecords; }

    public Integer getPageNumber() { return pageNumber; }
    public void setPageNumber(Integer pageNumber) { this.pageNumber = pageNumber; }

    public Integer getPageSize() { return pageSize; }
    public void setPageSize(Integer pageSize) { this.pageSize = pageSize; }
}