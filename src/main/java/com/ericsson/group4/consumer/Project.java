package com.ericsson.group4.consumer;


import java.util.List;

public class Project {
    private List<Components> components;

    public Project() {
    }

    public Project(List<Components> components) {
        this.components = components;
    }

    public List<Components> getComponents() {
        return components;
    }

    public void setComponents(List<Components> components) {
        this.components = components;
    }

    @Override
    public String toString() {
        return "com.ericsson.group4.consumer.Project{" +
                "projects=" + components +
                '}';
    }
}
