package com.ericsson.group4.repository;

import com.ericsson.group4.model.OwaspEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwaspEntityRepository extends CrudRepository<OwaspEntity, Integer> {

    List<OwaspEntity> findAllByProjectId(Integer projectId);

}
