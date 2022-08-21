package com.example.service;

import com.example.entity.Student;
import com.example.mapper.AbstractStudentMapperImpl;
import com.example.mapper.StudentMapperService;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Slf4j
public class StudentServiceImpl implements StudentService {

    @Inject
    MongoClient mongoClient;

    @Inject
    StudentMapperService studentMapperService;

    @ConfigProperty(name = "application.db.name")
    String dbName;

    @ConfigProperty(name = "application.db.collection")
    String collectionName;

    private MongoCollection<Document> getCollection() {
        log.info("getCollection: " + collectionName);
        log.info("getDatabase: " + dbName);
        return mongoClient.getDatabase(dbName).getCollection(collectionName);
    }

    @Override
    public void add(Student student) {
        getCollection().insertOne(studentMapperService.getDocumentFromStudent(student));
    }

    @Override
    public List<Student> getStudentList() {
        List<Student> students = new ArrayList<>();
        try (MongoCursor<Document> cursor = getCollection().find().iterator()) {
            while (cursor.hasNext()) {
                students.add(studentMapperService.getStudentFromDocument(cursor.next()));
            }
        }
        return students;
    }
}
