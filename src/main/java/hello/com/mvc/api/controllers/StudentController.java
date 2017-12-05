package hello.com.mvc.api.controllers;
import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import com.fasterxml.jackson.databind.ObjectMapper;
import hello.Application;
import hello.com.mvc.api.entities.Classes;
import hello.com.mvc.api.entities.Student;
import hello.com.mvc.api.service.IClassesService;
import hello.com.mvc.api.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController

@RequestMapping(value = { "/user", "/me" })
@CrossOrigin
public class StudentController {

    @Autowired
    private IStudentService studentService;

    @Autowired
    private IClassesService classesService;

    @GetMapping("meh")
    public Map<String, String> user(Principal principal) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("name", principal.getName());
        return map;
    }

    @GetMapping("all-students")
    public ResponseEntity<List<Student>> getAllStudents() {
        System.out.println("GETTING ALL STUDENTS");
        List<Student> list = studentService.getAllStudents();
        return new ResponseEntity<List<Student>>(list, HttpStatus.OK);
    }


    @GetMapping("student")
    public ResponseEntity<Student> getStudent(@RequestParam(value = "id") int id) {
        System.out.println("GETTING A STUDENT WITH AN ID");
        System.out.println("id is:"+ id);
        Student student = studentService.getStudent(id);
        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }


    @PostMapping("student")
    public ResponseEntity<Void> addStudent(@RequestBody Student student, UriComponentsBuilder builder) {

        Classes cls = new Classes();
        cls.setClass_1(student.getClasses().getClass_1());
        cls.setClass_2(student.getClasses().getClass_2());
        cls.setClass_3(student.getClasses().getClass_3());
        cls.setClass_4(student.getClasses().getClass_4());

        Student stdnt = new Student();
        System.out.println(student.getName());
        stdnt.setName(student.getName());
        stdnt.setMajor(student.getMajor());
        stdnt.setClasses(cls);

        boolean flag = studentService.addStudent(stdnt);

        if (flag == false) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/student?id={id}").buildAndExpand(student.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }


    @PutMapping("student")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {

        Classes cls = new Classes();
        cls.setClass_1(student.getClasses().getClass_1());
        cls.setClass_2(student.getClasses().getClass_2());
        cls.setClass_3(student.getClasses().getClass_3());
        cls.setClass_4(student.getClasses().getClass_4());
        cls.setId(student.getClasses().getId());

        classesService.updateClasses(cls);

        Student stdnt = new Student();
        stdnt.setName(student.getName());
        stdnt.setMajor(student.getMajor());

        studentService.updateStudent(student);
        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }

    @DeleteMapping ("student")
    public ResponseEntity<Void> deleteStudent(@RequestParam("id") int id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }




}
