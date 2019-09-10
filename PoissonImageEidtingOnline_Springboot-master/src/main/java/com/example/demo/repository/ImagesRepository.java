package com.example.demo.repository;
import org.bson.types.ObjectId;
import com.example.demo.document.Images;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImagesRepository extends MongoRepository<Images,ObjectId> {
        Images findByUsername(String name);
}
