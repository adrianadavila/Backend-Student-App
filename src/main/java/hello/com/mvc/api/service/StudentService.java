package hello.com.mvc.api.service;
import hello.com.mvc.api.dao.IStudentDAO;
import hello.com.mvc.api.entities.Student;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService implements IStudentService {
    @Autowired
    private IStudentDAO studentDAO;
    @Override
    public Student getStudent(int studentId) {
        Student obj = studentDAO.getStudent(studentId);
        return obj;
    }
    @Override
    public List<Student> getAllStudents(){
        return studentDAO.getAllStudents();
    }
    @Override
    public synchronized boolean addStudent(Student student){
        //ADRIANA--THIS BOOLEAN LOGIC is ok...stems from DAO.java file
        if (studentDAO.studentExists(student.getName(), student.getMajor())) {
            return false;
        } else {
            studentDAO.addStudent(student);
            return true;
        }
    }
    @Override
    public void updateStudent(Student student) {
        studentDAO.updateStudent(student);
    }
    @Override
    public void deleteStudent(int studentId) {
        studentDAO.deleteStudent(studentId);
    }
}