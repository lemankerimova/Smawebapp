package dao.impl;

import config.DBConfig;
import dao.SubjectDao;
import model.Group;
import model.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectDaoImpl implements SubjectDao {
    @Override
    public List<Subject> getAllSubjects() {
        List<Subject> subjects = new ArrayList<Subject>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT s.id, s.subject_name FROM subjects s WHERE s.active=1";
        try {
            c = DBConfig.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Subject subject = new Subject();
                    subject.setId(rs.getLong("id"));
                    subject.setSubjectName(rs.getNString("subject_name"));
                    subjects.add(subject);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                c.close();
                ps.close();
            } catch (SQLException ex) {
                ex.getMessage();
            }
        }
        return subjects;
    }

    @Override
    public List<Subject> getAllSubjectsWithout(Long id) {
        List<Subject> subjects = new ArrayList<Subject>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT s.id, s.subject_name FROM subjects s WHERE s.active=1 and s.id!=" + id;
        try {
            c = DBConfig.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Subject subject = new Subject();
                    subject.setId(rs.getLong("id"));
                    subject.setSubjectName(rs.getNString("subject_name"));
                    subjects.add(subject);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                c.close();
                ps.close();
            } catch (SQLException ex) {
                ex.getMessage();
            }
        }
        return subjects;
    }
}
