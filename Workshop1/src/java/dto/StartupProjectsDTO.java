/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author hoang
 */
    public class StartupProjectsDTO {
    private int project_id ;
    private String project_name;
    private String description ;
    private String Status;
    private Date estimated_launch;

   

    public StartupProjectsDTO() {
        this.project_id = 0;
        this.project_name = " ";
        this.description = " ";
        this.Status = " ";
        this.estimated_launch = null;
    }

    public StartupProjectsDTO(int project_id, String project_name, String description, String Status, Date estimated_launch) {
        this.project_id = project_id;
        this.project_name = project_name;
        this.description = description;
        this.Status = Status;
        this.estimated_launch = estimated_launch;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public Date getEstimated_launch() {
        return estimated_launch;
    }

    public void setEstimated_launch(Date estimated_launch) {
        this.estimated_launch = estimated_launch;
    }

    @Override
    public String toString() {
        return "StartupProjectsDTO{" + "project_id=" + project_id + ", project_name=" + project_name + ", description=" + description + ", Status=" + Status + ", estimated_launch=" + estimated_launch + '}';
    }
    
}

