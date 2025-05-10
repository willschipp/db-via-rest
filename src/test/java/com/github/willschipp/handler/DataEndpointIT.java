package com.github.willschipp.handler;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@SpringBootTest
@AutoConfigureMockMvc
public class DataEndpointIT {
    
    private static final Log logger = LogFactory.getLog(DataEndpointIT.class);

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testGenerate() throws Exception {
        mockMvc.perform(post("/api/generate")).andExpect(status().isCreated());
    }

    @Test
    public void testGetAll() throws Exception {
        String response = mockMvc.perform(get("/api/persons").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();
        logger.info(response);
    }

    @Test
    public void testDeleteAll() throws Exception {
        mockMvc.perform(delete("/api/purge")).andExpect(status().isNoContent());
    }
}
