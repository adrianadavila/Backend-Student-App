package hello.com.mvc.api.service;
import hello.com.mvc.api.entities.Student;

import java.util.List;

public interface IStudentService {
    List<Student> getAllStudents();

    Student getStudent(int studentId);

    //different return type from student dao b/c should let client know if was able to add or not
    boolean addStudent(Student student);

    void updateStudent(Student student);

    void deleteStudent(int studentId);
}