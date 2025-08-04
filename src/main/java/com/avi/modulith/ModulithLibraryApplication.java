package com.avi.modulith;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@RequiredArgsConstructor
@EnableAsync
public class ModulithLibraryApplication {


    public static void main(String[] args) {
        SpringApplication.run(ModulithLibraryApplication.class, args);
    }

}
