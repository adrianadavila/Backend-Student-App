package hello.com.mvc.api.controllers;
import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import hello.com.mvc.api.entities.Student;
import hello.com.mvc.api.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController

@RequestMapping(value = { "/user"})
@CrossOrigin
public class StudentController {

    @Autowired
    private IStudentService studentService;

    @GetMapping("all-students")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> list = studentService.getAllStudents();
        return new ResponseEntity<List<Student>>(list, HttpStatus.OK);
    }


    @GetMapping("student")
    public ResponseEntity<Student> getStudent(@RequestParam(value = "id") int id) {

        Student student = studentService.getStudent(id);
        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }


    @PostMapping("student")
    public ResponseEntity<Void> addStudent(@RequestBody Student student, UriComponentsBuilder builder) {


        boolean flag = studentService.addStudent(student);

        if (flag == false) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/student?id={id}").buildAndExpand(student.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }


    @PutMapping("student")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {

        studentService.updateStudent(student);
        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }

    @DeleteMapping ("student")
    public ResponseEntity<Void> deleteStudent(@RequestParam("id") int id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }




}
