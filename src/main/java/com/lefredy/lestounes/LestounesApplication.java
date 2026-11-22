package com.lefredy.lestounes;

import com.mongodb.MongoClientSettings;
import org.bson.UuidRepresentation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LestounesApplication {

    public static void main(String[] args) {

        SpringApplication.run(LestounesApplication.class, args);
    }

}
