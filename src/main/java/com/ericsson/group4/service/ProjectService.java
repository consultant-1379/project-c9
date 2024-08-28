package com.ericsson.group4.service;

import com.ericsson.group4.model.ProjectEntity;
import com.ericsson.group4.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public ProjectEntity saveProject(ProjectEntity project) {
        return projectRepository.save(project);
    }

    public ProjectEntity findOneById(Integer id) {
        return projectRepository.findOneById(id);
    }

    public List<ProjectEntity> findAll() {
        return projectRepository.findAll();
    }


}
