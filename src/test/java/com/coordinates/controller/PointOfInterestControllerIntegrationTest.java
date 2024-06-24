package com.coordinates.controller;

import com.coordinates.XyincApplication;
import com.coordinates.model.PointOfInterest;
import com.coordinates.persistence.PointOfInterestRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(classes = XyincApplication.class)
@AutoConfigureMockMvc
public class PointOfInterestControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PointOfInterestRepository pointOfInterestRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        pointOfInterestRepository.deleteAll();

        PointOfInterest poi1 = new PointOfInterest("POI1", 10, 20);
        PointOfInterest poi2 = new PointOfInterest("POI2", 15, 25);
        PointOfInterest poi3 = new PointOfInterest("POI3", 30, 40);
        pointOfInterestRepository.saveAll(Arrays.asList(poi1, poi2, poi3));
    }

    @Test
    public void testListPointsOfInterest() throws Exception {
        mockMvc.perform(get("/api/pointsofinterest"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void testCreatePointOfInterest() throws Exception {
        PointOfInterest newPoi = new PointOfInterest("Novo POI", 5, 15);
        mockMvc.perform(post("/api/pointsofinterest")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newPoi)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("Novo POI")));
    }

    @Test
    public void testListPointsOfInterestByProximity() throws Exception {
        mockMvc.perform(get("/api/pointsofinterest/12/22/10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2))); // POI1 e POI2 devem estar próximos
    }
}
