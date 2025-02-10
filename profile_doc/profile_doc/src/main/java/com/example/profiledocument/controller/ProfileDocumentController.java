package com.example.profiledocument.controller;

import com.example.profiledocument.model.ProfileDocument;
import com.example.profiledocument.service.ProfileDocumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/profile-doc")
public class ProfileDocumentController {

    private final ProfileDocumentService service;

    public ProfileDocumentController(ProfileDocumentService service) {
        this.service = service;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String id = service.AddProfessionalDocument(file);
            return ResponseEntity.ok("File uploaded successfully, ID: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<String> updateFile(@PathVariable String id, @RequestParam("file") MultipartFile file) {
        try {
            String response = service.UpdateProfessionalDocument(id, file);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProfileDocument> getDocument(@PathVariable String id) {
        try {
            ProfileDocument document = service.GetProfessionalDocument(id);
            return ResponseEntity.ok(document);
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFile(@PathVariable String id) {
        try {
            String response = service.DeleteProfessionalDocument(id);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}
