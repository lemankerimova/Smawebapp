package dao;

import model.Subject;

import java.util.List;

public interface SubjectDao {
    List<Subject> getAllSubjects();

    List<Subject> getAllSubjectsWithout(Long id);
}
