package com.ericsson.group4.model;

import com.ericsson.group4.consumer.SeverityType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Map;
import java.util.Objects;

import static com.ericsson.group4.consumer.SeverityType.*;

@Entity
public class OwaspEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int projectId;
    private String category;
    private int numOfBlocker;
    private int numOfCritical;
    private int numOfMajor;
    private int numOfMinor;
    private int numOfInfo;

    public OwaspEntity() {
        this.numOfBlocker = 0;
        this.numOfCritical = 0;
        this.numOfMajor = 0;
        this.numOfMinor = 0;
        this.numOfInfo = 0;
    }

    public OwaspEntity(Integer projectId, String category, Map<SeverityType, Integer> severity) {
        this.projectId = projectId;
        this.category = category;
        this.numOfBlocker = severity.getOrDefault(BLOCKER, 0);
        this.numOfCritical = severity.getOrDefault(CRITICAL, 0);
        this.numOfMajor = severity.getOrDefault(MAJOR, 0);
        this.numOfMinor = severity.getOrDefault(MINOR, 0);
        this.numOfInfo = severity.getOrDefault(INFO, 0);
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getNumOfBlocker() {
        return numOfBlocker;
    }

    public void setNumOfBlocker(int numOfBlocker) {
        this.numOfBlocker = numOfBlocker;
    }

    public Integer getNumOfCritical() {
        return numOfCritical;
    }

    public void setNumOfCritical(int numOfCritical) {
        this.numOfCritical = numOfCritical;
    }

    public Integer getNumOfMajor() {
        return numOfMajor;
    }

    public void setNumOfMajor(int numOfMajor) {
        this.numOfMajor = numOfMajor;
    }

    public Integer getNumOfMinor() {
        return numOfMinor;
    }

    public void setNumOfMinor(int numOfMinor) {
        this.numOfMinor = numOfMinor;
    }

    public Integer getNumOfInfo() {
        return numOfInfo;
    }

    public void setNumOfInfo(int numOfInfo) {
        this.numOfInfo = numOfInfo;
    }

    @Override
    public String toString() {
        return "OwaspEntity{" +
                "id=" + id +
                ", projectId=" + projectId +
                ", category='" + category + '\'' +
                ", numOfBlocker=" + numOfBlocker +
                ", numOfCritical=" + numOfCritical +
                ", numOfMajor=" + numOfMajor +
                ", numOfMinor=" + numOfMinor +
                ", numOfInfo=" + numOfInfo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OwaspEntity that = (OwaspEntity) o;
        return id == that.id &&
                projectId == that.projectId &&
                numOfBlocker == that.numOfBlocker &&
                numOfCritical == that.numOfCritical &&
                numOfMajor == that.numOfMajor &&
                numOfMinor == that.numOfMinor &&
                numOfInfo == that.numOfInfo &&
                category.equals(that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, projectId, category, numOfBlocker, numOfCritical, numOfMajor, numOfMinor, numOfInfo);
    }
}
