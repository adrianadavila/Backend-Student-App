package hello.com.mvc.api.dao;

import java.util.List;

import hello.com.mvc.api.entities.Student;

public interface IStudentDAO {

    List<Student> getAllStudents();

    Student getStudent(int studentId);

    void addStudent(Student student);

    void updateStudent(Student student);

    void deleteStudent(int studentId);

    boolean studentExists(String name, String major);
}