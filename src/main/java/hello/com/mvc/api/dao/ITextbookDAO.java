package hello.com.mvc.api.dao;


import hello.com.mvc.api.entities.Textbook;

import java.util.List;

public interface ITextbookDAO {
    List<Textbook> getAllTextbooks();

    List<Textbook> getAllTextbooksByStudentId(int studentId);

    Textbook getTextbookById(int textbookId);

    void addTextbook(Textbook textbook);

    void updateTextbook(Textbook newTextbook);

    void deleteTextbookById(int textbookId);

    boolean textbookExists(String textbookTitle, int studentId);
}
