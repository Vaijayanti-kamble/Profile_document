package com.example.profiledocument.service;

import com.example.profiledocument.utility.Utility;
import com.example.profiledocument.model.ProfileDocument;
import com.google.cloud.firestore.*;
import com.google.cloud.storage.*;
import com.google.cloud.storage.Blob;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDateTime;
import com.google.cloud.storage.BlobInfo;
import java.util.concurrent.ExecutionException;

@Service
public class ProfileDocumentService {
    private static final String BUCKET_NAME = "documentdoc";
    private static final String COLLECTION_NAME = "profile_doc";
    private final Storage storage;
    private final Firestore firestore;

    // Inject Firestore and Storage via constructor
    public ProfileDocumentService(Storage storage, Firestore firestore) {
        this.storage = storage;
        this.firestore = firestore;
    }

    // Adds a professional document
    public String AddProfessionalDocument(MultipartFile file) throws Exception {
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Blob blob = storage.create(
                BlobInfo.newBuilder(BUCKET_NAME, fileName).setContentType(file.getContentType()).build(),
                file.getBytes()
        );

        String fileUrl = blob.getMediaLink();
        String formattedDate = Utility.getTime(LocalDateTime.now());

        DocumentReference docRef = firestore.collection(COLLECTION_NAME).document();
        ProfileDocument document = new ProfileDocument();
        document.setId(docRef.getId());
        document.setProfile_Doc_Url(fileUrl);
        document.setCreated_Date(formattedDate);
        document.setUpdated_Date(formattedDate);

        docRef.set(document);

        return docRef.getId();
    }

    // Updates a professional document
    public String UpdateProfessionalDocument(String documentId, MultipartFile newFile) throws Exception {
        DocumentReference docRef = firestore.collection(COLLECTION_NAME).document(documentId);
        DocumentSnapshot snapshot = docRef.get().get();

        if (!snapshot.exists()) {
            throw new Exception("Document not found!");
        }

        ProfileDocument document = snapshot.toObject(ProfileDocument.class);
        String oldFileUrl = document.getProfile_Doc_Url();

        if (oldFileUrl != null && !oldFileUrl.isEmpty()) {
            String oldFileName = oldFileUrl.substring(oldFileUrl.lastIndexOf("/") + 1);
            Blob oldBlob = storage.get(BUCKET_NAME, oldFileName);
            if (oldBlob != null) {
                oldBlob.delete();  // Only delete if file exists
            } else {
                System.out.println("Old file not found in the bucket, skipping deletion.");
            }
        }

        String newFileName = System.currentTimeMillis() + "_" + newFile.getOriginalFilename();
        Blob newBlob = storage.create(
                BlobInfo.newBuilder(BUCKET_NAME, newFileName).setContentType(newFile.getContentType()).build(),
                newFile.getBytes()
        );

        String newFileUrl = newBlob.getMediaLink();
        document.setProfile_Doc_Url(newFileUrl);
        document.setUpdated_Date(Utility.getTime(LocalDateTime.now()));
        docRef.set(document);

        return "File updated successfully, new URL: " + newFileUrl;
    }

    // Gets a professional document by its ID
    public ProfileDocument GetProfessionalDocument(String id) throws ExecutionException, InterruptedException {
        DocumentSnapshot snapshot = firestore.collection(COLLECTION_NAME).document(id).get().get();

        if (snapshot.exists()) {
            return snapshot.toObject(ProfileDocument.class);
        }
        return null;
    }

    // Deletes a professional document by its ID
    public String DeleteProfessionalDocument(String documentId) throws Exception {
        DocumentReference docRef = firestore.collection(COLLECTION_NAME).document(documentId);
        DocumentSnapshot snapshot = docRef.get().get();

        if (!snapshot.exists()) {
            throw new Exception("Document not found!");
        }

        ProfileDocument document = snapshot.toObject(ProfileDocument.class);
        String fileUrl = document.getProfile_Doc_Url();

        if (fileUrl != null && !fileUrl.isEmpty()) {
            String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
            Blob blob = storage.get(BUCKET_NAME, fileName);
            if (blob != null) {
                blob.delete(); // Delete file from bucket
            } else {
                System.out.println("File not found in the bucket, skipping deletion.");
            }
        }

        docRef.delete();
        return "File deleted successfully.";
    }
}
