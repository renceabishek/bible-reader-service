package com.read.bible.service.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class FirebaseConfiguration {

  @Value("${firebase.path}")
  private String path;


  @Value("${firebase.database-url}")
  private String databaseUrl;

  @Value("${firebase.storage-url}")
  private String storageUrl;

  @Value("${GOOGLE_CREDENTIALS}")
  private String gservicesConfig;

  @Bean
  public FirebaseApp provideFirebaseOptions() throws IOException {
    JSONObject jsonObject = new JSONObject(gservicesConfig.toString());
    InputStream is = new ByteArrayInputStream(jsonObject.toString().getBytes());

    FirebaseOptions options = new FirebaseOptions.Builder()
        .setCredentials(GoogleCredentials.fromStream((is)))
        .setDatabaseUrl(databaseUrl)
        .setStorageBucket(storageUrl)
        .build();

    return FirebaseApp.initializeApp(options);
  }

//  @Bean
//  @Qualifier("storage-main")
//  public Storage getStorage() throws IOException {
//
//    return StorageOptions.newBuilder()
//        .setCredentials(GoogleCredentials.fromStream((gservicesConfig.getInputStream())))
//        .build().getService();
//  }


  @Bean
  @Qualifier("main")
  public DatabaseReference provideDatabaseReference(FirebaseApp firebaseApp) {
    FirebaseDatabase
        .getInstance(firebaseApp)
        .setPersistenceEnabled(false);
    return FirebaseDatabase
        .getInstance(firebaseApp)
        .getReference();
  }
}
