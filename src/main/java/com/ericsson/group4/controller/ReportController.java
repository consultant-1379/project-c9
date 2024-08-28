package com.ericsson.group4.controller;

import com.ericsson.group4.consumer.Consumer;
import com.ericsson.group4.model.OwaspEntity;
import com.ericsson.group4.model.ProjectEntity;
import com.ericsson.group4.repository.OwaspEntityRepository;
import com.ericsson.group4.repository.ProjectRepository;
import com.ericsson.group4.service.OwaspEntityService;
import com.ericsson.group4.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestOperations;

import java.util.ArrayList;


@Controller
public class ReportController {

    @Autowired
    private Consumer consumer;

    @Autowired
    private RestOperations restOperations;

    @Autowired
    private OwaspEntityRepository owaspEntityRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private OwaspEntityService owaspEntityService;

    @PostMapping("/report")
    public String showReport(@RequestParam Integer id, Model model) {
        Iterable<OwaspEntity> items = owaspEntityService.findAllByProjectId(id);
        ProjectEntity project = projectService.findOneById(id);
        if (items.spliterator().getExactSizeIfKnown() == 0) {
            consumer.fetchAndSaveOwaspFromA1ToA10(restOperations, project);
            Iterable<OwaspEntity> fresh = owaspEntityService.findAllByProjectId(id);
            items = fresh;
        }
        for (OwaspEntity item : items) {
            model.addAttribute(item.getCategory(), item);
        }
        return "report";
    }

    @GetMapping("")
    public String showUI(Model model) {
        consumer.saveProjectService(restOperations);
        Iterable<ProjectEntity> obj = projectRepository.findAll();
        ArrayList<ProjectEntity> projects = new ArrayList<>();
        for (ProjectEntity item : obj) {
            projects.add(item);
            model.addAttribute("projects", projects);
        }
        return "index";
    }
}
