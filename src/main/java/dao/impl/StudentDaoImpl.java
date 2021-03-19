package dao.impl;

import config.DBConfig;
import dao.StudentDao;
import model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    @Override
    public List<Student> getAllStudentInfo() {
        List<Student> students = new ArrayList<Student>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT s.id student_id,s.s_name,s.surname,s.age,s.seria_num,sci.phone,sci.email FROM student s LEFT JOIN " +
                "student_contact_info sci ON s.contact_info_id = sci.id  WHERE s.active=1";
        try {
            c = DBConfig.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Student student = new Student();
                    student.setId(rs.getLong("id"));
                    student.setName(rs.getString("name"));
                    student.setSurname(rs.getString("surname"));
                    student.setSeriaNum(rs.getNString("seria_num"));
                    student.setDOB(rs.getString("age"));
                    student.setPhone(rs.getString("phone"));
                    student.setEmail(rs.getString("email"));
                    students.add(student);
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
        return students;
    }


    @Override
    public boolean saveStudent(Student student) {
        boolean isAdded = false;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String insertIntoStudentContactInfo = "INSERT INTO student_contact_info (phone,email) VALUES(?,?)";

        String getLastStudentInfoId = "SELECT MAX(id) id FROM student_contact_info";

        String insertIntoStudent = "INSERT INTO student(s_name,surname,age,seria_num,gender,contact_info_id) VALUES(?,?,?,?,?,?)";

        c = DBConfig.getConnection();
        if (c != null) {
            try {
                ps = c.prepareStatement(insertIntoStudentContactInfo);
                ps.setString(1, student.getPhone());
                ps.setString(2, student.getEmail());
                ps.execute();

                ps = c.prepareStatement(getLastStudentInfoId);
                rs = ps.executeQuery();
                int lastStudentInfoId = 0;
                if (rs.next()) {
                    lastStudentInfoId = rs.getInt("id");
                }

                ps = c.prepareStatement(insertIntoStudent);
                ps.setString(1, student.getName());
                ps.setString(2, student.getSurname());
                ps.setString(3, student.getDOB());
                ps.setString(4, student.getSeriaNum());
                ps.setString(5, student.getGender());
                ps.setLong(6, lastStudentInfoId);
                ps.execute();
                isAdded = true;
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            } finally {
                try {
                    ps.close();
                    c.close();
                } catch (Exception e) {

                }
            }
        }
        return isAdded;
    }

    @Override
    public Student getStudentsByGroupid(Long studentId) {
        Student student = new Student();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT s.id, s.s_name, s.surname, sci.phone,sci.email,s.gender \n" +
                "FROM group_student gs INNER JOIN\n" +
                "    student s ON s.id = gs.student_id\n" +
                "        INNER JOIN\n" +
                "    student_contact_info sci ON s.contact_info_id = sci.id\n" +
                "WHERE  gs.group_id = 1";

        try {
            c = DBConfig.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    student.setId(rs.getLong("student_id"));
                    student.setName(rs.getString("name"));
                    student.setSurname(rs.getString("surname"));
                    student.setSeriaNum(rs.getNString("seria_num"));
                    student.setDOB(rs.getString("age"));
                    student.setPhone(rs.getString("phone"));
                    student.setEmail(rs.getString("email"));
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
        return student;
    }

    @Override
    public boolean updateStudent(Student student) {
        boolean isUpdated = false;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String updateStudent = "UPDATE student SET s_name = ?,surname = ?,age = ?,seria_num = ?,gender = ? WHERE id = ?";

        String selectContactInfoById = "SELECT contact_info_id FROM student WHERE id=" + student.getId();

        String updateStudentContactInfo = "UPDATE student_contact_info SET email = ?,phone = ? WHERE id = ?";

        c = DBConfig.getConnection();
        if (c != null) {
            try {
                ps = c.prepareStatement(updateStudent);
                ps.setString(1, student.getName());
                ps.setString(2, student.getSurname());
                ps.setString(3, student.getDOB());
                ps.setString(4, student.getSeriaNum());
                ps.setString(5, student.getGender());
                ps.setLong(6, student.getId());
                ps.execute();

                ps = c.prepareStatement(selectContactInfoById);
                rs = ps.executeQuery();
                long contactInfoId = 0;
                if (rs.next()) {
                    contactInfoId = rs.getLong("contact_info_id");
                }
                ps = c.prepareStatement(updateStudentContactInfo);
                ps.setString(1, student.getEmail());
                ps.setString(2, student.getPhone());
                ps.setLong(3, contactInfoId);
                ps.execute();

                isUpdated = true;
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            } finally {
                try {
                    ps.close();
                    c.close();
                } catch (Exception e) {

                }
            }
        }
        return isUpdated;
    }

//    @Override
//    public boolean softDeleteStudentById(Long id) {
//        boolean isUpdated = false;
//        Connection c = null;
//        PreparedStatement ps = null;
//        String updateStudent = "UPDATE student SET active = 0 WHERE id = ?";
//        try {
//            c = DBConfig.getConnection();
//            if (c != null) {
//                ps = c.prepareStatement(updateStudent);
//                ps.setLong(1, id);
//                ps.execute();
//                isUpdated = true;
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        } finally {
//            try {
//                c.close();
//                ps.close();
//            } catch (Exception e) {
//
//            }
//            return isUpdated;
//        }
//    }
}
