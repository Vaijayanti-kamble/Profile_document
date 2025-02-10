package com.example.profiledocument.service;


import com.example.profiledocument.model.UserProfessionRequest;
import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserProfessionRequestService {

    private static final String COLLECTION_NAME = "user_profession_requests";

    @Autowired
    private Firestore firestore;


    public String createRequest(UserProfessionRequest details) throws Exception {
        CollectionReference collection = firestore.collection(COLLECTION_NAME);

        // Generate unique ID for the document
        String id = UUID.randomUUID().toString();
        Timestamp timestamp = Timestamp.now();
        // Set default values
        details.setcreated_Date(timestamp.toDate());
        details.setupdated_Date(timestamp.toDate());
        details.setstatus("Pending");

        // Convert the object into a map to avoid serialization issues
        Map<String, Object> documentData = new HashMap<>();
        documentData.put("first_Name", details.getfirst_Name());
        documentData.put("last_Name", details.getlast_Name());
        documentData.put("created_Date", details.getcreated_Date().toString());
        documentData.put("updated_Date", details.getupdated_Date().toString());
        documentData.put("request_Detail", details.getrequest_Detail());
        documentData.put("status", details.getstatus());


        try {
            DocumentReference document = collection.document(id);
            ApiFuture<WriteResult> future = document.set(documentData);
            return future.get().getUpdateTime().toString();
        } catch (Exception e) {
            throw new Exception("Error saving to Firestore: " + e.getMessage(), e);
        }
    }
}

