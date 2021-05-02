package org.bitnick.portfolio.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    private String position;
    private String description;
    private String startMonthStr;
    private String endMonthStr;
    private String company;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartMonthStr() {
        return startMonthStr;
    }

    public void setStartMonthStr(String startMonthStr) {
        this.startMonthStr = startMonthStr;
    }

    public String getEndMonthStr() {
        return endMonthStr;
    }

    public void setEndMonthStr(String endMonthStr) {
        this.endMonthStr = endMonthStr;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
