package dao.impl;

import config.DBConfig;
import dao.TeacherDao;
import model.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDaoImpl implements TeacherDao {
    @Override
    public List<Teacher> getAllTeachersForComboBox() {
        List<Teacher> teachers = new ArrayList<Teacher>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT t.id, t.t_name, t.surname FROM teacher t WHERE active=1";
        try {
            c = DBConfig.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Teacher teacher = new Teacher();
                    teacher.setId(rs.getLong("id"));
                    teacher.setName(rs.getString("t_name"));
                    teacher.setSurname(rs.getString("surname"));
                    teachers.add(teacher);
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
        return teachers;
    }

    @Override
    public List<Teacher> getAllTeachersForComboBox(Long id) {
        List<Teacher> teachers = new ArrayList<Teacher>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT t.id, t.t_name, t.surname FROM teacher t WHERE active=1 and t.id!=" + id;
        try {
            c = DBConfig.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Teacher teacher = new Teacher();
                    teacher.setId(rs.getLong("id"));
                    teacher.setName(rs.getString("t_name"));
                    teacher.setSurname(rs.getString("surname"));
                    teachers.add(teacher);
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
        return teachers;
    }
}

