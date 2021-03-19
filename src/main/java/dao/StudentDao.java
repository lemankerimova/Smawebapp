package dao;

import model.Student;

import java.util.List;

public interface StudentDao {

    List<Student> getAllStudentInfo();

    boolean saveStudent(Student student);
    Student getStudentsByGroupid(Long studentId);

    boolean updateStudent(Student student);
//    boolean softDeleteStudentById(Long id);

}
