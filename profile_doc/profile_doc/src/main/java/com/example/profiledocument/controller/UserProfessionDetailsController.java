package com.example.profiledocument.controller;

import com.example.profiledocument.model.UserProfessionDetails;
import com.example.profiledocument.service.UserProfessionDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-profession-details")
public class UserProfessionDetailsController {

    private final UserProfessionDetailsService service;

    public UserProfessionDetailsController(UserProfessionDetailsService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> saveUserProfessionDetails(@RequestBody UserProfessionDetails details) {
        try {
            String id = service.saveUserProfessionDetails(details);
            return ResponseEntity.ok("Saved with ID: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserProfessionDetailsById(@PathVariable String id) {
        try {
            UserProfessionDetails details = service.getUserProfessionDetailsById(id);
            if (details != null) {
                return ResponseEntity.ok(details);
            } else {
                return ResponseEntity.status(404).body("No document found with ID: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserProfessionDetails(@PathVariable String id) {
        try {
            String result = service.deleteUserProfessionDetails(id);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}
