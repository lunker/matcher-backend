package org.lunker.matcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * Created by dongqlee on 2018. 1. 8..
 */

@EntityScan(basePackages = {"org.lunker.matcher", "org.lunker.matcher_common.model"})
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
