package hello.com.mvc.api.service;
import hello.com.mvc.api.entities.Textbook;

import java.util.List;

public interface ITextbookService {
     List<Textbook> getAllTextbooks();

    Textbook getTextbook(int textbookId);
    //different return type from student dao b/c should let client know if was able to add or not

    List<Textbook>getAllTextbooksByStudentId(int studentId);

    boolean addTextbook(Textbook textbook);

    void updateTextbook(Textbook textbook);

    void deleteTextbook(int textbookId);
}
