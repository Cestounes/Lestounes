package com.lefredy.lestounes.repository;

import com.lefredy.lestounes.schemas.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

public interface MongoUserRepository extends MongoRepository<User, UUID> {
    Optional<User> findByUsername(String username);
    
}
