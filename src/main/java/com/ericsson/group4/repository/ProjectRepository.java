package com.ericsson.group4.repository;

import com.ericsson.group4.model.ProjectEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends CrudRepository<ProjectEntity, Long> {

    ProjectEntity findOneById(Integer id);

    List<ProjectEntity> findAll();

}
