package service;

import model.Subject;

import java.util.List;

public interface SubjectService {
    List<Subject> getAllSubjects();

    List<Subject> getAllSubjectsWithout(Long id);
}
