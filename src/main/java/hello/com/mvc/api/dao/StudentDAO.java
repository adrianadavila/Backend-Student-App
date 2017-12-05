package hello.com.mvc.api.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import hello.com.mvc.api.entities.Student;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Repository
public class StudentDAO implements IStudentDAO {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Student getStudent(int studentId) {
        return entityManager.find(Student.class, studentId);
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<Student> getAllStudents() {
        String hql = "select s from Student s"; //Student b/c it is the 'entity'
        return (List<Student>) entityManager.createQuery(hql).getResultList();
    }
    @Override
    public void addStudent(Student student) {
        entityManager.persist(student);
    }
    @Override
    public void updateStudent(Student student) {
        Student stdnt = getStudent(student.getId());
        stdnt.setName(student.getName());
        stdnt.setMajor(student.getMajor());
        //stdnt.setClasses(student.getClasses());
        entityManager.flush();
    }
    @Override
    public void deleteStudent(int studentId) {
        entityManager.remove(getStudent(studentId));
    }
    @Override
    public boolean studentExists(String name, String major) {
        String hql = "FROM Student S WHERE S.name = ? and S.major = ?";
        int count = entityManager.createQuery(hql).setParameter(1, name)
                .setParameter(2, major).getResultList().size();
        return count > 0 ? true : false;
    }
}