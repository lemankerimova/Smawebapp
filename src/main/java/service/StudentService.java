package service;

import model.Student;

import java.util.List;

public interface StudentService {

    List<Student> getAllStudents();

    boolean saveStudent(Student student);
    Student getStudentsByGroupid(Long studentId);

    boolean updateStudent(Student student);
//    boolean softDeleteStudent(Long id);

}
