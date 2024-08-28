package com.ericsson.group4.consumer;

public class Components {
    private String key;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "com.ericsson.group4.consumer.Components{" +
                "name='" + name + "', key='" + key + '\'' +
                '}';
    }
}
