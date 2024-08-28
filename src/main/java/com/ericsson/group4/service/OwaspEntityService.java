package com.ericsson.group4.service;

import com.ericsson.group4.model.OwaspEntity;
import com.ericsson.group4.repository.OwaspEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OwaspEntityService {

    @Autowired
    private OwaspEntityRepository owaspEntityRepository;

    public OwaspEntity saveOwaspEntity(OwaspEntity owaspEntity) {
        return owaspEntityRepository.save(owaspEntity);
    }


    public List<OwaspEntity> findAllByProjectId(int projectId) {
        return owaspEntityRepository.findAllByProjectId(projectId);
    }


}
