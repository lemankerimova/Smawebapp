package service;

import model.Teacher;

import java.util.List;

public interface TeacherService {
    List<Teacher> getAllTeachersForComboBox();

    List<Teacher> getAllTeachersForComboBoxWithout(Long id);
}
