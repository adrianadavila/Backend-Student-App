package hello.com.mvc.api.service;

import hello.com.mvc.api.dao.ITextbookDAO;
import hello.com.mvc.api.entities.Textbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("textbookService")
public class TextbookService implements ITextbookService{
    @Autowired
    private ITextbookDAO textbookDAO;

    @Override
    public List<Textbook> getAllTextbooks(){
        return textbookDAO.getAllTextbooks();
    }

    @Override
    public List<Textbook>getAllTextbooksByStudentId(int studentId){
        return textbookDAO.getAllTextbooksByStudentId(studentId);
    }

    @Override
    public Textbook getTextbook(int textbookId){
        Textbook obj = textbookDAO.getTextbookById(textbookId);
        return obj;

    }

    @Override
    //no student can checkout the same textbook twice
    public boolean addTextbook(Textbook textbook){
        if (textbookDAO.textbookExists(textbook.getText_title(), textbook.getStudent_id())) {
            return false;
        } else {
            textbookDAO.addTextbook(textbook);
            return true;
        }
    }

    @Override
    public void updateTextbook(Textbook textbook){
        textbookDAO.updateTextbook(textbook);
    }

    @Override
    public void deleteTextbook(int textbookId){
        textbookDAO.deleteTextbookById(textbookId);
    }
}
