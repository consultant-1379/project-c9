package com.ericsson.group4.consumer;


import java.util.List;

public class OWASP {
    private long total;
    private List<Issues> issues;

    public OWASP() {
    }

    public OWASP(long total, List<Issues> issues) {
        this.total = total;
        this.issues = issues;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<Issues> getIssues() {
        return issues;
    }

    public void setIssues(List<Issues> issues) {
        this.issues = issues;
    }

    @Override
    public String toString() {
        return "com.ericsson.group4.consumer.OWASP{" +
                "total=" + total +
                ", issues=" + issues +
                '}';
    }
}
