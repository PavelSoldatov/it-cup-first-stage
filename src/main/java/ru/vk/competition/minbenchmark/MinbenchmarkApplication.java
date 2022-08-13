package ru.vk.competition.minbenchmark;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class MinbenchmarkApplication {

    public static void main(String[] args) {
        log.info("APPLICATION STARTED");
        SpringApplication.run(MinbenchmarkApplication.class, args);
    }
}
