package com.example.profiledocument.model;


import java.util.Date;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfessionRequest {



    @NotEmpty(message = "First name is required")
    private String first_Name;

    @NotEmpty(message = "Last name is required")
    private String last_Name;

    @NotNull(message = "Created date is required")
    private Date created_Date;

    @NotNull(message = "Updated date is required")
    private Date updated_Date;

    @NotEmpty(message = "Request detail is required")
    private String request_Detail;

    @NotEmpty(message = "Status is required")
    private String status;


    public String getfirst_Name() {
        return first_Name;
    }

    public void setfirst_Name(String first_Name) {
        this.first_Name = first_Name;
    }
    public String getlast_Name() {
        return last_Name;
    }

    public void setlast_Name(String last_Name) {
        this.last_Name = last_Name;
    }
    public Date getcreated_Date() {
        return created_Date;
    }

    public void setcreated_Date(Date created_Date) {
        this.created_Date = created_Date;
    }
    public Date getupdated_Date() {
        return updated_Date;
    }

    public void setupdated_Date(Date updated_Date) {
        this.updated_Date = updated_Date;
    }
    public String getrequest_Detail() {
        return request_Detail;
    }

    public void setrequest_Detail(String request_Detail) {
        this.request_Detail = request_Detail;
    }
    public String getstatus() {
        return status;
    }

    public void setstatus(String status) {
        this.status = status;
    }
}
