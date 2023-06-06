package hr.algebra.springproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


public abstract class BaseControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    protected String getAuthorizationHeader() throws Exception {
        final Map<String, Object> body = new HashMap<>();
        body.put("username", "filip");
        body.put("password", "password");

        final MvcResult mvcResult = mockMvc.perform(
                post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body))
        ).andReturn();

        final String accessToken = JsonPath.read(mvcResult.getResponse().getContentAsString(), "$.accessToken");
        return "Bearer " + accessToken;
    }
}
