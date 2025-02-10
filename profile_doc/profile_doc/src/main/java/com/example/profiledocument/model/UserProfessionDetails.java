package com.example.profiledocument.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfessionDetails {

    private String Document_Id;

    @NotEmpty(message = "First name is required")
    private String First_Name;

    @NotEmpty(message = "Middle name is required")
    private String Middle_Name;

    @NotEmpty(message = "Last name is required")
    private String Last_Name;

    @NotEmpty(message = "Phone number is required")
    private String Phone_No;

    @NotEmpty(message = "Email ID is required")
    private String Email_Id;

    @NotNull(message = "Created date is required")
    private String created_Date;

    @NotNull(message = "Updated date is required")
    private String updated_Date;

    @NotEmpty(message = "Main profession is required")
    private String Main_Profession;

    @NotEmpty(message = "Other professions are required")
    private String Other_Professions;



    public String getDocument_Id() {
        return Document_Id;
    }

    public void setDocument_Id(String Document_Id) {
        this.Document_Id = Document_Id;
    }
    public String getFirst_Name() {
        return First_Name;
    }

    public void setFirst_Name(String First_Name) {
        this.First_Name = First_Name;
    }
    public String getMiddle_Name() {
        return Middle_Name;
    }

    public void setMiddle_Name(String Middle_Name) {
        this.Middle_Name = Middle_Name;
    }
    public String getLast_Name() {
        return Last_Name;
    }

    public void setLast_Name(String Last_Name) {
        this.Last_Name = Last_Name;
    }
    public String getPhone_No() {
        return Phone_No;
    }

    public void setPhone_No(String Phone_No) {
        this.Phone_No = Phone_No;
    }
    public String getEmail_Id() {
        return Email_Id;
    }

    public void setEmail_Id(String Email_Id) {
        this.Email_Id = Email_Id;
    }

    public String getMain_Profession() {
        return Main_Profession;
    }

    public void setMain_Profession(String Main_Profession) {
        this.Main_Profession = Main_Profession;
    }
    public String getOther_Professions() {
        return Other_Professions;
    }

    public void setOther_Professions(String Other_Professions) {
        this.Other_Professions = Other_Professions;
    }


    public void setCreated_Date(String created_Date) {
        this.created_Date = created_Date;
    }

    public String getCreated_Date() {
        return created_Date;
    }

    public void setUpdated_Date(String updated_Date) {
        this.updated_Date = updated_Date;
    }

    public String getUpdated_Date() {
        return updated_Date;
    }

}
