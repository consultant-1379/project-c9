package com.ericsson.group4.consumer;

import com.ericsson.group4.model.OwaspEntity;
import com.ericsson.group4.model.ProjectEntity;
import com.ericsson.group4.repository.ProjectRepository;
import com.ericsson.group4.service.OwaspEntityService;
import com.ericsson.group4.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static com.ericsson.group4.consumer.SeverityType.*;


@Service
public class Consumer {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private OwaspEntityService owaspEntityService;

    private static final Logger log = LoggerFactory.getLogger(Consumer.class);


    public void saveProjectService(RestOperations restOperations) {
        Project project = restOperations.getForObject("http://sonarqube:9000/api/components/search?qualifiers=TRK", Project.class);
        List<Components> projects = project.getComponents();
        List<ProjectEntity> same = projectService.findAll();
        if (same.spliterator().getExactSizeIfKnown() == 0) {
            for (Components item : projects) {
                ProjectEntity dummy = new ProjectEntity(item.getName(), item.getKey());
                projectService.saveProject(dummy);
            }
        }
    }

    public void fetchAndSaveOwaspFromA1ToA10(RestOperations restOperations, ProjectEntity project) {
        String projectKey = project.getKey();
        StringBuilder url = new StringBuilder("http://sonarqube:9000/api/issues/search?componentKeys=");
        String postfix = "&owaspTop10=a";
        url.append(projectKey + postfix);
        for (int i = 1; i <= 10; i++) {
            url.append(i);
            OWASP owasp = restOperations.getForObject(url.toString(), OWASP.class);
            List<Issues> issues = owasp.getIssues();
            String category = "a" + i;
            Map<SeverityType, Integer> severity = new EnumMap<>(SeverityType.class);
            severity.put(BLOCKER, 0);
            severity.put(CRITICAL, 0);
            severity.put(MAJOR, 0);
            severity.put(MINOR, 0);
            severity.put(INFO, 0);
            for (Issues issue : issues) {
                switch (SeverityType.valueOf(issue.getSeverity())) {
                    case BLOCKER:
                        severity.put(BLOCKER, severity.get(BLOCKER) + 1);
                        break;
                    case CRITICAL:
                        severity.put(CRITICAL, severity.get(CRITICAL) + 1);
                        break;
                    case MAJOR:
                        severity.put(MAJOR, severity.get(MAJOR) + 1);
                        break;
                    case MINOR:
                        severity.put(MINOR, severity.get(MINOR) + 1);
                        break;
                    case INFO:
                        severity.put(INFO, severity.get(INFO) + 1);
                        break;
                }
            }

            OwaspEntity owaspEntity = new OwaspEntity((int) project.getId(), category, severity);
            OwaspEntity savedOwaspEntity = owaspEntityService.saveOwaspEntity(owaspEntity);
            log.info("saved {}", savedOwaspEntity);
            url.setLength(url.length() - 1);

        }
    }
}
