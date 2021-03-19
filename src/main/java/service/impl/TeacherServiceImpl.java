package service.impl;

import dao.TeacherDao;
import model.Teacher;
import service.TeacherService;

import java.util.List;

public class TeacherServiceImpl implements TeacherService {
    private TeacherDao teacherDao;

    public TeacherServiceImpl(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    @Override
    public List<Teacher> getAllTeachersForComboBox() {
        return teacherDao.getAllTeachersForComboBox();
    }

    @Override
    public List<Teacher> getAllTeachersForComboBoxWithout(Long id) {
        return teacherDao.getAllTeachersForComboBox(id);
    }
}
