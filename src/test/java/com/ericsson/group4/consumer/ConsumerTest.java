package com.ericsson.group4.consumer;

import com.ericsson.group4.model.ProjectEntity;
import com.ericsson.group4.repository.OwaspEntityRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestOperations;

import java.util.Arrays;

import static org.mockito.Mockito.*;


@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase
public class ConsumerTest {

    @Autowired
    private Consumer consumer;

    @Autowired
    private OwaspEntityRepository owaspEntityRepository;

    @Mock
    private RestOperations restOperations;

    @Test
    public void testReportsIssuesAreStoredCorrectly() {

        OWASP owasp = new OWASP();
        Issues issue1 = new Issues();
        Issues issue2 = new Issues();
        Issues issue3 = new Issues();
        Issues issue4 = new Issues();
        Issues issue5 = new Issues();
        issue1.setSeverity(SeverityType.BLOCKER.name());
        issue2.setSeverity(SeverityType.CRITICAL.name());
        issue3.setSeverity(SeverityType.MAJOR.name());
        issue4.setSeverity(SeverityType.MINOR.name());
        issue5.setSeverity(SeverityType.INFO.name());
        owasp.setIssues(Arrays.asList(issue1, issue2, issue3, issue4, issue5));

        when(restOperations.getForObject(anyString(), any())).thenReturn(owasp);
        ProjectEntity projectEntity = new ProjectEntity("test", "test1");
        projectEntity.setId(1);

        consumer.fetchAndSaveOwaspFromA1ToA10(restOperations, projectEntity);

        Assert.assertEquals(10, owaspEntityRepository.findAllByProjectId(1).size());
    }

}
