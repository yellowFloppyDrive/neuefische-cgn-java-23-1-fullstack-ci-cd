package de.neuefische.timemanagement.backend.controller;

import de.neuefische.timemanagement.backend.repository.TaskRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    TaskRepo taskRepo;

    @BeforeEach
    void setUp() {

    }

    @Test
    @DirtiesContext
    void getAllTasks() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/tasks/"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "[]"));
    }

    @Test
    @DirtiesContext
    void addTask() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/tasks/")
                        .contentType(MediaType.APPLICATION_JSON).content("""               
                                {"id": null, "title": "task 1","dateTime": "2023-02-23T09:32:27.325Z" }
                                    """))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        """                        
                                {"title": "task 1","dateTime": "2023-02-23T09:32:27.325" }
                                    """
                )).andExpect(jsonPath("$.id").isNotEmpty());
    }



    @Test
    @DirtiesContext
    void addTaskNotValidDateTime() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/tasks/")
                        .contentType(MediaType.APPLICATION_JSON).content("""               
                                {"id": null, "title": "task 1","dateTime": "Non valid DateTime" }
                                    """))
                .andExpect(status().isBadRequest());
    }




}