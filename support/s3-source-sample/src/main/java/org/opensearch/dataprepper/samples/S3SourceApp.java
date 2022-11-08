package org.opensearch.dataprepper.samples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class S3SourceApp {
    public static void main(final String[] args) {
        SpringApplication.run(S3SourceApp.class, args);
    }

    @Bean
    OrderGenerator orderGenerator() {
        return new OrderGenerator();
    }
}
