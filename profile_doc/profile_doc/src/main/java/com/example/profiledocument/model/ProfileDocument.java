package com.example.profiledocument.model;

public class ProfileDocument {
    private String id;
    private String profile_Doc_Url;
    private String created_Date;
    private String updated_Date;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {

        this.id = id;
    }

    public String getProfile_Doc_Url() {
        return profile_Doc_Url;
    }

    public void setProfile_Doc_Url(String profile_Doc_Url) {

        this.profile_Doc_Url = profile_Doc_Url;
    }

    public String getCreated_Date() {

        return created_Date;
    }

    public void setCreated_Date(String created_Date) {

        this.created_Date = created_Date;
    }

    public String getUpdated_Date() {

        return updated_Date;
    }

    public void setUpdated_Date(String updated_Date) {


        this.updated_Date = updated_Date;
    }
}
