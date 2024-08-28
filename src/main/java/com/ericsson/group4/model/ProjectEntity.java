package com.ericsson.group4.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    private String name;
    private String projectKey;

    public ProjectEntity() {
    }

    public ProjectEntity(String name, String projectKey) {
        this.name = name;
        this.projectKey = projectKey;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return projectKey;
    }

    public void setKey(String key) {
        this.projectKey = key;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + "', key='" + projectKey + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectEntity project = (ProjectEntity) o;
        return id == project.id &&
                name.equals(project.name) && projectKey.equals(project.projectKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, projectKey);
    }
}
