package com.ericsson.group4.service;

import com.ericsson.group4.consumer.Components;
import com.ericsson.group4.consumer.Project;
import com.ericsson.group4.model.ProjectEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;


@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ProjectServiceTest {

    @Autowired
    private ProjectService projectService;


    @Test
    public void testToSaveProjectEntity() {
        ProjectEntity savedProject = projectService.saveProject(new ProjectEntity("TestProject", "TestKey"));
        Assert.assertEquals("TestProject", savedProject.getName());
    }

    @Test
    public void testToFindOneById() {
        projectService.saveProject(new ProjectEntity("TestProject1", "TestKey1"));
        projectService.saveProject(new ProjectEntity("TestProject2", "TestKey2"));
        projectService.saveProject(new ProjectEntity("TestProject3", "TestKey3"));

        Assert.assertEquals("TestProject3", projectService.findOneById(3).getName());

    }

    @Test
    public void testToFindAll() {
        projectService.saveProject(new ProjectEntity("TestProject4", "TestKey4"));
        projectService.saveProject(new ProjectEntity("TestProject5", "TestKey5"));
        projectService.saveProject(new ProjectEntity("TestProject6", "TestKey6"));
        projectService.saveProject(new ProjectEntity("TestProject7", "TestKey7"));
        projectService.saveProject(new ProjectEntity("TestProject8", "TestKey8"));


        Assert.assertEquals(5, projectService.findAll().size());
    }

    @Test
    public void testToSetAndGetProjectEntity() {
        ProjectEntity pE1 = new ProjectEntity();
        pE1.setId(8);
        Assert.assertEquals(8, pE1.getId());
        String expected = "Project{id=8, name='null', key='null'}";
        Assert.assertEquals(expected, pE1.toString());

        pE1.setName("project");
        Assert.assertEquals("project", pE1.getName());

        pE1.setKey("key");
        Assert.assertEquals("key", pE1.getKey());

        ProjectEntity pE2 = new ProjectEntity();
        pE2.setId(8);
        pE2.setName("project");
        pE2.setKey("key");
        Assert.assertTrue(pE2.equals(pE1) && pE1.equals(pE2));
        Assert.assertTrue(pE2.hashCode() == pE1.hashCode());
    }

    @Test
    public void testingForProjectAndComponent() {
        Components components1 = new Components();
        components1.setName("SomeComponents");
        Assert.assertEquals("SomeComponents", components1.getName());
        components1.setKey("SomeKeys");
        Assert.assertEquals("SomeKeys", components1.getKey());
        Components components2 = new Components();
        components2.setName("lotsComponents");
        Assert.assertEquals("lotsComponents", components2.getName());
        components2.setKey("lotsKeys");
        Assert.assertEquals("lotsKeys", components2.getKey());
        String expected1 = "com.ericsson.group4.consumer.Components{name='lotsComponents', key='lotsKeys'}";
        Assert.assertEquals(expected1, components2.toString());


        List<Components> components = Arrays.asList(components1, components2);
        Project project1 = new Project(components);
        Assert.assertEquals(2, project1.getComponents().size());
        Project project2 = new Project();
        project2.setComponents(components);
        String expected2 = "com.ericsson.group4.consumer.Project{projects=[com.ericsson.group4.consumer.Components{name='SomeComponents', key='SomeKeys'}, com.ericsson.group4.consumer.Components{name='lotsComponents', key='lotsKeys'}]}";
        Assert.assertEquals(expected2, project2.toString());

    }
}
