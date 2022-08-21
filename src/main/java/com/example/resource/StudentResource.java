package com.example.resource;

import com.example.dto.StudentDto;
import com.example.entity.Student;
import com.example.mapper.StudentMapperService;
import com.example.service.StudentService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/students")
public class StudentResource {

    @Inject
    StudentService studentService;

    @Inject
    StudentMapperService studentMapperService;

    @GET
    @Path("get")
    @Produces(MediaType.APPLICATION_JSON)
    public List<StudentDto> list() {
        return studentMapperService.studentToDto(studentService.getStudentList());
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<StudentDto> add(StudentDto studentDto) {
        studentService.add(studentMapperService.dtoToStudent(studentDto));
        return list();
    }
}
