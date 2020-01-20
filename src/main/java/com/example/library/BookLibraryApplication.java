package com.example.library;

import com.example.library.configuration.LibraryProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.library.repository")
@EnableConfigurationProperties(LibraryProperties.class)
public class BookLibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookLibraryApplication.class, args);
    }

}
