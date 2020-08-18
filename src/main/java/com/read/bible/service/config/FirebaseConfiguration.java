package com.read.bible.service.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.read.bible.service.config.properties.GithubProperties;
import com.read.bible.service.config.properties.ServiceApiKeyProperties;
import com.read.bible.service.config.properties.UrlProperties;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class FirebaseConfiguration {

  private final ServiceApiKeyProperties serviceApiKeyProperties;
  private final UrlProperties urlProperties;
  private final GithubProperties githubProperties;

  @Value(value = "classpath:google-services.json")
  private Resource gservicesConfig;

  public FirebaseConfiguration(ServiceApiKeyProperties serviceApiKeyProperties,
      UrlProperties urlProperties,
      GithubProperties githubProperties) {
    this.serviceApiKeyProperties = serviceApiKeyProperties;
    this.urlProperties = urlProperties;
    this.githubProperties = githubProperties;
  }


  @Bean
  public FirebaseApp initializeFirebaseApp() throws IOException {

    InputStream inputStream;

    if (serviceApiKeyProperties.getCredentials() == null) {
      inputStream = gservicesConfig.getInputStream();
    } else {
      JSONObject jsonObject = new JSONObject(serviceApiKeyProperties.getCredentials());
      inputStream = new ByteArrayInputStream(jsonObject.toString().getBytes());
    }

    FirebaseOptions options = new FirebaseOptions.Builder()
        .setCredentials(GoogleCredentials.fromStream((inputStream)))
        .setDatabaseUrl(urlProperties.getDatabase())
        .setStorageBucket(urlProperties.getStorage())
        .build();

    return FirebaseApp.initializeApp(options);
  }
}
