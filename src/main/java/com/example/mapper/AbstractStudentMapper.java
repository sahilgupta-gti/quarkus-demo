package com.example.mapper;

import com.example.dto.StudentDto;
import com.example.entity.Student;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public abstract class AbstractStudentMapper {

    private static final String ENROLLMENT_NUMBER = "enrollmentNumber";
    private static final String FIELD_ID = "_id";
    private static final String FIELD_SOURCE = "_source";

    @Mapping(target = "id", source = ENROLLMENT_NUMBER)
    public abstract Student DtoToStudent(StudentDto studentDto);

    @Mapping(source = "id", target = ENROLLMENT_NUMBER)
    public abstract StudentDto dtoToStudent(Student student);

    public abstract List<StudentDto> dtoToStudent(List<Student> student);
    public abstract List<Student> DtoToStudent(List<StudentDto> studentDto);


    public Student getStudentFromDocument(Document document) {
        Student student = new Student();
        student.setName(document.getString("name"));
        student.setAge(document.getInteger("age"));
        if(document.getObjectId(FIELD_ID) != null) {
            student.setId(document.getObjectId(FIELD_ID).toString());
        }
        student.setType(document.getString("_type"));
        if(document.getObjectId(FIELD_SOURCE) != null) {
            student.setSource(document.getObjectId(FIELD_SOURCE).toString());
        }
        return student;
    }

    public Document getDocumentFromStudent(Student student) {
        return new Document()
                .append("name", student.getName())
                .append("age", student.getAge())
                .append("_type", student.getType())
                .append(FIELD_SOURCE, ObjectId.get());
    }

}
