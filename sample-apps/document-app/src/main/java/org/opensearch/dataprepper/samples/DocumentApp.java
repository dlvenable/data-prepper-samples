package org.opensearch.dataprepper.samples;

import com.amazonaws.xray.javax.servlet.AWSXRayServletFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.servlet.Filter;

@SpringBootApplication
public class DocumentApp {
    public static void main(final String[] args) {
        SpringApplication.run(DocumentApp.class, args);
    }

    @Bean
    public Filter TracingFilter() {
        return new AWSXRayServletFilter("ApiApp");
    }
}
