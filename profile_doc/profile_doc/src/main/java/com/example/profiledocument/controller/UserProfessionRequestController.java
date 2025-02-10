package com.example.profiledocument.controller;

import com.example.profiledocument.model.UserProfessionRequest;
import com.example.profiledocument.service.UserProfessionRequestService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-profession-requests")

public class UserProfessionRequestController {

    private final UserProfessionRequestService service;

    public UserProfessionRequestController(UserProfessionRequestService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> saveUserProfessionRequest(@Valid @RequestBody UserProfessionRequest details) {
        try {

            String id = service.createRequest(details);

            return ResponseEntity.ok("Request saved successfully at : " + id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}
