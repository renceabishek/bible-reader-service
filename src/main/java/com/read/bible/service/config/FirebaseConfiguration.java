package com.read.bible.service.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class FirebaseConfiguration {

  @Value("${firebase.path}")
  private String path;

  @Value(value = "GOOGLE_APPLICATION_CREDENTIALS")
  private Resource gservicesConfig;

  @Value("${firebase.database-url}")
  private String databaseUrl;

  @Value("${firebase.storage-url}")
  private String storageUrl;

  @Bean
  public FirebaseApp provideFirebaseOptions() throws IOException {
    FirebaseOptions options = new FirebaseOptions.Builder()
        .setCredentials(GoogleCredentials.fromStream((gservicesConfig.getInputStream())))
        .setDatabaseUrl(databaseUrl)
        .setStorageBucket(storageUrl)
        .build();

    return FirebaseApp.initializeApp(options);
  }

  @Bean
  @Qualifier("storage-main")
  public Storage getStorage() throws IOException {

    return StorageOptions.newBuilder()
        .setCredentials(GoogleCredentials.fromStream((gservicesConfig.getInputStream())))
        .build().getService();
  }


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
