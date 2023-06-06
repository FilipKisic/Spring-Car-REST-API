package hr.algebra.springproject.controller;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@AutoConfigureMockMvc
@SpringBootTest
class CarControllerTest extends BaseControllerTest {

    @Test
    void getAll() throws Exception {
        mockMvc.perform(
                        get("/cars/all")
                                .header(HttpHeaders.AUTHORIZATION, getAuthorizationHeader())
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().encoding(StandardCharsets.ISO_8859_1))
                .andExpect(jsonPath("$", hasSize(7)));
    }

    @Test
    void getById() throws Exception {
        mockMvc.perform(
                        get("/cars/1")
                                .header(HttpHeaders.AUTHORIZATION, getAuthorizationHeader())
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().encoding(StandardCharsets.ISO_8859_1))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.brand").value("Mercedes-Benz"))
                .andExpect(jsonPath("$.model").value("C63 AMG"))
                .andExpect(jsonPath("$.color").value("Red"))
                .andExpect(jsonPath("$.powerInHp").value(462));
    }

    @Test
    @Transactional
    void createCar() throws Exception {
        final Map<String, Object> body = new HashMap<>();
        body.put("brand", "Porsche");
        body.put("model", "911 GT3 RS");
        body.put("color", "Racing Yellow");
        body.put("powerInHp", 525);

        mockMvc.perform(
                        post("/cars")
                                .header(HttpHeaders.AUTHORIZATION, getAuthorizationHeader())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(body))
                )
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    void updateCar() throws Exception {
        final Map<String, Object> body = new HashMap<>();
        body.put("id", 8);
        body.put("brand", "Porsche");
        body.put("model", "911 GT3 RS");
        body.put("color", "Racing Yellow");
        body.put("powerInHp", 518);

        mockMvc.perform(
                        put("/cars")
                                .header(HttpHeaders.AUTHORIZATION, getAuthorizationHeader())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(body))
                )
                .andExpect(status().isOk());
    }

    @Test
    void deleteCarById() throws Exception {
        mockMvc.perform(
                        delete("/cars/1")
                                .header(HttpHeaders.AUTHORIZATION, getAuthorizationHeader())
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    @Test
    void deleteCar() throws Exception {
        final Map<String, Object> body = new HashMap<>();
        body.put("brand", "Porsche");
        body.put("model", "911 GT3 RS");
        body.put("color", "Racing Yellow");
        body.put("powerInHp", 525);

        mockMvc.perform(
                        delete("/cars")
                                .header(HttpHeaders.AUTHORIZATION, getAuthorizationHeader())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(body))
                )
                .andExpect(status().isOk());
    }
}
