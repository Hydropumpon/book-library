package com.example.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.library.repository")
public class BookLibraryApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(BookLibraryApplication.class, args);
	}

}
