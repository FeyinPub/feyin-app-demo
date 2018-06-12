package net.feyin.app.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class FeyinAppDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeyinAppDemoApplication.class, args);
	}
}
