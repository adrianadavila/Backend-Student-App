package hello.com.mvc.api.controllers;
import hello.com.mvc.api.entities.Textbook;
import hello.com.mvc.api.service.ITextbookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@RestController
@RequestMapping(value = { "/user", "/me" })
@CrossOrigin
public class TextbookController {

    @Autowired
    private ITextbookService textbookService;

    //get all textbooks in db
    @GetMapping("all-textbook")
    public ResponseEntity<List<Textbook>> getAllTextbooks() {
        List<Textbook> list = textbookService.getAllTextbooks();
        return new ResponseEntity<List<Textbook>>(list, HttpStatus.OK);
    }

    //get all textbooks checked out by one student
    @GetMapping("student-textbook-collection")
    public ResponseEntity<List<Textbook>> getStudentTextbookCollection(@RequestParam(value = "studentId") int studentId){

        List<Textbook> list = textbookService.getAllTextbooksByStudentId(studentId);
        return new ResponseEntity<List<Textbook>>(list, HttpStatus.OK);
    }

    //grab a specific textbook by its id
    @GetMapping("textbook")
    public ResponseEntity<Textbook> getTextbook(@RequestParam(value = "id") int id) {

        Textbook textbook = textbookService.getTextbook(id);
        return new ResponseEntity<Textbook>(textbook, HttpStatus.OK);
    }


    //add a textbook
    @PostMapping("textbook")
    public ResponseEntity<Void> addTextbook(@RequestBody Textbook textbook, UriComponentsBuilder builder) {

        boolean flag = textbookService.addTextbook(textbook);

        if (flag == false) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/textbook?id={id}").buildAndExpand(textbook.getIdtextbook()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }


    //update textbook
    @PutMapping("textbook")
    public ResponseEntity<Textbook> updateTextbook(@RequestBody Textbook textbook) {

        textbookService.updateTextbook(textbook);
        return new ResponseEntity<Textbook>(textbook, HttpStatus.OK);
    }

    //delete textbook
    @DeleteMapping ("textbook")
    public ResponseEntity<Void> deleteTextbook(@RequestParam("id") int id) {
        textbookService.deleteTextbook(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
