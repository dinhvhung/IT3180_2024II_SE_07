package com.example.service_apa.demo.xsx.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * APIClient giúp JavaFX giao tiếp với API Spring Boot thông qua HTTP.
 */
public class APIClient {
    private static final String BASE_URL = "http://localhost:8080/api"; // Thay đổi URL nếu cần

    private static final HttpClient client = HttpClient.newHttpClient();

    /**
     * Gửi request POST với dữ liệu JSON đến API Spring Boot.
     *
     * @param endpoint  Đường dẫn API (ví dụ: "/register")
     * @param jsonBody  Chuỗi JSON chứa dữ liệu cần gửi
     * @return Phản hồi từ API dưới dạng chuỗi
     * @throws Exception Nếu có lỗi xảy ra
     */
    public static String sendPostRequest(String endpoint, String jsonBody) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + endpoint))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    /**
     * Gửi request GET để lấy dữ liệu từ API Spring Boot.
     *
     * @param endpoint  Đường dẫn API (ví dụ: "/residents")
     * @return Dữ liệu JSON từ API
     * @throws Exception Nếu có lỗi xảy ra
     */
    public static String sendGetRequest(String endpoint) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + endpoint))
                .header("Accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}

