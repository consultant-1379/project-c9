package com.ericsson.group4.service;

import com.ericsson.group4.consumer.Issues;
import com.ericsson.group4.consumer.OWASP;
import com.ericsson.group4.consumer.SeverityType;
import com.ericsson.group4.model.OwaspEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class OwaspEntityServiceTest {

    @Autowired
    private OwaspEntityService owaspEntityService;

    @Test
    public void testToSaveOwaspEntity() {

        Map<SeverityType, Integer> severity = new EnumMap<>(SeverityType.class);

        OwaspEntity owaspEntity = new OwaspEntity(7, "savedOne", severity);


        OwaspEntity savedOwaspEntity = owaspEntityService.saveOwaspEntity(owaspEntity);

        Assert.assertEquals(owaspEntity, savedOwaspEntity);
    }

    @Test
    public void testToFindAllOwaspEntityByProjectId() {

        Map<SeverityType, Integer> severity = new EnumMap<>(SeverityType.class);
        OwaspEntity owaspEntity1 = new OwaspEntity(1, "test1", severity);
        OwaspEntity owaspEntity2 = new OwaspEntity(3, "test2", severity);
        OwaspEntity owaspEntity3 = new OwaspEntity(1, "test3", severity);

        owaspEntityService.saveOwaspEntity(owaspEntity1);
        owaspEntityService.saveOwaspEntity(owaspEntity2);
        owaspEntityService.saveOwaspEntity(owaspEntity3);

        Assert.assertEquals(2, owaspEntityService.findAllByProjectId(1).size());
    }

    @Test
    public void testToSetAndGetOwaspEntity() {

        OwaspEntity oE1 = new OwaspEntity();
        oE1.setProjectId(55);
        Assert.assertEquals((Integer) 55, oE1.getProjectId());

        oE1.setCategory("INFO");
        Assert.assertEquals("INFO", oE1.getCategory());

        oE1.setNumOfBlocker(9);
        Assert.assertEquals((Integer) 9, oE1.getNumOfBlocker());

        oE1.setNumOfCritical(8);
        Assert.assertEquals((Integer) 8, oE1.getNumOfCritical());

        oE1.setNumOfMajor(7);
        Assert.assertEquals((Integer) 7, oE1.getNumOfMajor());

        oE1.setNumOfMinor(6);
        Assert.assertEquals((Integer) 6, oE1.getNumOfMinor());

        oE1.setNumOfInfo(5);
        Assert.assertEquals((Integer) 5, oE1.getNumOfInfo());

        OwaspEntity oE2 = new OwaspEntity();
        oE2.setProjectId(55);
        Assert.assertEquals((Integer) 55, oE2.getProjectId());

        oE2.setCategory("INFO");
        Assert.assertEquals("INFO", oE2.getCategory());

        oE2.setNumOfBlocker(9);
        Assert.assertEquals((Integer) 9, oE2.getNumOfBlocker());

        oE2.setNumOfCritical(8);
        Assert.assertEquals((Integer) 8, oE2.getNumOfCritical());

        oE2.setNumOfMajor(7);
        Assert.assertEquals((Integer) 7, oE2.getNumOfMajor());

        oE2.setNumOfMinor(6);
        Assert.assertEquals((Integer) 6, oE2.getNumOfMinor());

        oE2.setNumOfInfo(5);
        Assert.assertEquals((Integer) 5, oE2.getNumOfInfo());
        //OwaspEntity oE3 = oE2;
        Assert.assertTrue(oE1.equals(oE2) && oE2.equals(oE1));
        Assert.assertTrue(oE1.hashCode() == oE2.hashCode());
        String expected = "OwaspEntity{id=0, projectId=55, category='INFO', numOfBlocker=9, numOfCritical=8, numOfMajor=7, numOfMinor=6, numOfInfo=5}";
        Assert.assertEquals(expected, oE2.toString());

    }

    @Test
    public void testingForOWASPAndIssues() {
        OWASP o1 = new OWASP();
        o1.setTotal(100);
        Assert.assertEquals(100, o1.getTotal());

        Issues i1 = new Issues();
        i1.setSeverity("Blocker");
        Assert.assertEquals("Blocker", i1.getSeverity());
        Issues i2 = new Issues();
        i2.setSeverity("Info");
        Assert.assertEquals("Info", i2.getSeverity());
        List<Issues> issues = Arrays.asList(i1, i2);
        o1.setIssues(issues);
        Assert.assertEquals(2, o1.getIssues().size());

        String expected1 = "com.ericsson.group4.consumer.Issues{" +
                "severity='" + "Blocker" + '\'' +
                '}';
        Assert.assertEquals(expected1, i1.toString());


        List<Issues> issuesList = Arrays.asList(i1, i2);
        OWASP owasp = new OWASP(1, issuesList);
        System.out.println(owasp);
        String expected2 = "com.ericsson.group4.consumer.OWASP{total=1, issues=[com.ericsson.group4.consumer.Issues{severity='Blocker'}, " +
                "com.ericsson.group4.consumer.Issues{severity='Info'}]}";
        Assert.assertEquals(expected2, owasp.toString());


    }
}
