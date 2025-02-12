package com.example.profiledocument.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.cloud.StorageClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.InputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig {

    private static final String BUCKET_NAME = "documentdoc"; // Replace with your actual bucket name

    @Bean
    public Firestore firestore() throws IOException {
        InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("profession-details-141cb6c65513.json");

        if (serviceAccount == null) {
            throw new IOException("Service account file not found");
        }

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setProjectId("profession-details")  // Replace with your Firebase project ID
                .build();

        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
        }

        return FirestoreClient.getFirestore();
    }

    @Bean
    public StorageClient storageClient() throws IOException {
        if (FirebaseApp.getApps().isEmpty()) {
            throw new IOException("FirebaseApp is not initialized");
        }
        return StorageClient.getInstance();
    }
}

