package com.example.demo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan({"com.example.demo.Algorithms","com.example.demo", "com.example.demo.controller","repository", "com.example.demo.services","config", "com.example.demo.document","repository", "com.example.demo.services"})
public class FileUploadApplication {
	public static void main(String[] args) {
		SpringApplication.run(FileUploadApplication.class, args);
	}
}
