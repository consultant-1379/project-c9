package com.ericsson.group4.controller;

import com.ericsson.group4.consumer.Components;
import com.ericsson.group4.consumer.Project;
import com.ericsson.group4.consumer.SeverityType;
import com.ericsson.group4.model.OwaspEntity;
import com.ericsson.group4.model.ProjectEntity;
import com.ericsson.group4.repository.OwaspEntityRepository;
import com.ericsson.group4.repository.ProjectRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestOperations;

import java.util.*;

import static com.ericsson.group4.consumer.SeverityType.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class ReportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OwaspEntityRepository owaspEntityRepository;

    @MockBean
    private ProjectRepository projectRepository;

    @MockBean
    private RestOperations restOperations;

    @Test
    public void testToShowReportPage() throws Exception {

        Map<SeverityType, Integer> severity = new EnumMap<>(SeverityType.class);
        severity.put(BLOCKER, 2);
        severity.put(CRITICAL, 3);
        severity.put(MAJOR, 4);
        severity.put(MINOR, 5);
        severity.put(INFO, 6);

        List<OwaspEntity> owaspEntities = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            owaspEntities.add(new OwaspEntity(1, "a" + i, severity));
        }
        when(owaspEntityRepository.findAllByProjectId(anyInt())).thenReturn(owaspEntities);

        ProjectEntity projectEntity = new ProjectEntity("test", "test1");
        projectEntity.setId(1);
        when(projectRepository.findOneById(anyInt())).thenReturn(projectEntity);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/report?id=1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());


    }

    @Test
    public void testToShowUI() throws Exception {
        when(restOperations.getForObject(any(String.class), any())).thenReturn(new Project(Collections.singletonList(new Components())));

        ProjectEntity projectEntity = new ProjectEntity("test", "test1");
        projectEntity.setId(1);
        when(projectRepository.findAll()).thenReturn(Collections.singletonList(projectEntity));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());


    }

}