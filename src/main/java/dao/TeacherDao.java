package dao;

import model.Teacher;

import java.util.List;

public interface TeacherDao {
    List<Teacher> getAllTeachersForComboBox();

    List<Teacher> getAllTeachersForComboBox(Long id);
}
