package service.impl;

import dao.SubjectDao;
import model.Group;
import model.Subject;
import service.SubjectService;

import java.util.List;

public class SubjectServiceImpl implements SubjectService {

    private SubjectDao subjectDao;

    public SubjectServiceImpl(SubjectDao subjectDao) {
        this.subjectDao = subjectDao;
    }

    @Override
    public List<Subject> getAllSubjects() {
        return subjectDao.getAllSubjects();
    }

    @Override
    public List<Subject> getAllSubjectsWithout(Long id) {
        return subjectDao.getAllSubjectsWithout(id);
    }

}
