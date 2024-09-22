package com.distance.model;

import java.util.List;

public class ApiResponse {
    private String status;
    private String message;
    private List<String> zipCodes; // For successful responses

    // Constructor for success response
    public ApiResponse(String status, List<String> zipCodes) {
        this.status = status;
        this.zipCodes = zipCodes;        
        this.message = "Zip codes within the specified radius have been found";
    }

    // Constructor for error response
    public ApiResponse(String status, String message) {
        this.status = status;
        this.zipCodes = null; // No zip codes for error
        this.message = message;
    }

    // Getters and setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getZipCodes() {
        return zipCodes;
    }

    public void setZipCodes(List<String> zipCodes) {
        this.zipCodes = zipCodes;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}