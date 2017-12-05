package hello.com.mvc.api.controllers;

import hello.com.mvc.api.entities.Classes;
import hello.com.mvc.api.entities.Student;
import hello.com.mvc.api.service.IClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@RestController
@RequestMapping(value="/restful")
@CrossOrigin
public class ClassesController {

    @Autowired
    private IClassesService classesService;

    @GetMapping("all-classes")
    public ResponseEntity<List<Classes>> getAllStudents() {
        System.out.println("GETTING ALL STUDENTS");
        List<Classes> list = classesService.getAllClasses();
        return new ResponseEntity<List<Classes>>(list, HttpStatus.OK);
    }

    @GetMapping("classes")
    public ResponseEntity<Classes> getClasses(@RequestParam(value = "id") int id) {
        Classes classes = classesService.getClasses(id);
        return new ResponseEntity<Classes>(classes, HttpStatus.OK);
    }

    @PutMapping("classes")
    public ResponseEntity<Classes> updateClasses(@RequestBody Classes classes) {
        classesService.updateClasses(classes);
        return new ResponseEntity<Classes>(classes, HttpStatus.OK);
    }


}
