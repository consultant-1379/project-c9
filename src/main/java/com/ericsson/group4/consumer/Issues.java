package com.ericsson.group4.consumer;


public class Issues {
    private String severity;

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    @Override
    public String toString() {
        return "com.ericsson.group4.consumer.Issues{" +
                "severity='" + severity + '\'' +
                '}';
    }
}
