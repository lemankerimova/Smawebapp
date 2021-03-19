package service.impl;

import dao.StudentDao;
import model.Student;
import service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao;

    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentDao.getAllStudentInfo();
    }

        @Override
    public boolean saveStudent(Student student) {
        return studentDao.saveStudent(student);
    }

    @Override
    public Student getStudentsByGroupid(Long studentId) {
        return studentDao.getStudentsByGroupid(studentId);
    }

    @Override
    public boolean updateStudent(Student student) {
        return studentDao.updateStudent(student);
    }

//    @Override
//    public boolean softDeleteStudent(Long id) {
//        return studentDao.softDeleteStudentById(id);
//    }
}
