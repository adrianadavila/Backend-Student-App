package hello.com.mvc.api.dao;


import hello.com.mvc.api.entities.Student;
import hello.com.mvc.api.entities.Textbook;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.soap.Text;
import java.util.List;

@Transactional
@Repository

public class TextbookDAO implements ITextbookDAO{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Textbook> getAllTextbooks() {
        String hql = "select s from Textbook s"; //Student b/c it is the 'entity'
        return (List<Textbook>) entityManager.createQuery(hql).getResultList();
    }

    @Override
    public List<Textbook> getAllTextbooksByStudentId(int studentId){
        String hql = "FROM Textbook C WHERE C.student_id = ?";
        return entityManager.createQuery(hql).setParameter(1, studentId)
                .getResultList();

    }

    @Override
    public Textbook getTextbookById(int textbookId) {
        return entityManager.find(Textbook.class, textbookId);
    }
    @SuppressWarnings("unchecked")

    @Override
    public void addTextbook(Textbook textbook) {
        entityManager.persist(textbook);
    }

    @Override
    public void updateTextbook(Textbook textbook) {
        Textbook newTextbook = getTextbookById(textbook.getIdtextbook());
        newTextbook.setText_title(textbook.getText_title());

        entityManager.flush();
    }

    @Override
    public void deleteTextbookById(int textbookId) {
        entityManager.remove(getTextbookById(textbookId));
    }

    @Override
    public boolean textbookExists(String textbookTitle, int studentId) {
    //check to see that this textbook is not already associated with a student
        String hql = "FROM Textbook C WHERE C.text_title = ? and C.student_id = ? ";
        int count = entityManager.createQuery(hql).setParameter(1, textbookTitle)
                .setParameter(2, studentId)
                .getResultList().size();
        return count > 0 ? true : false;


    }
}
