package servlet;

import dao.GroupDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TeacherDao;
import dao.impl.GroupDaoImpl;
import dao.impl.StudentDaoImpl;
import dao.impl.SubjectDaoImpl;
import dao.impl.TeacherDaoImpl;
import model.Group;
import model.Subject;
import model.Teacher;
import service.GroupService;
import service.StudentService;
import service.SubjectService;
import service.TeacherService;
import service.impl.GroupServiceImpl;
import service.impl.StudentServiceImpl;
import service.impl.SubjectServiceImpl;
import service.impl.TeacherServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/groups")
public class GroupServlet extends HttpServlet {

    GroupDao groupDAO = new GroupDaoImpl();
    GroupService groupService = new GroupServiceImpl(groupDAO);

    TeacherDao teacherDao = new TeacherDaoImpl();
    TeacherService teacherService = new TeacherServiceImpl(teacherDao);

    SubjectDao subjectDao = new SubjectDaoImpl();
    SubjectService subjectService = new SubjectServiceImpl(subjectDao);

    StudentDao studentDao = new StudentDaoImpl();
    StudentService studentService = new StudentServiceImpl(studentDao);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        try {
            String action = null;
            String address = "";

            if (request.getParameter("action") != null) {
                action = request.getParameter("action");
            }
            if (action.equalsIgnoreCase("saveGroup")) {

                Group group = new Group();
                group.setGroupName(request.getParameter("groupName"));

                Teacher teacher = new Teacher();
                teacher.setId(Long.parseLong(request.getParameter("teacher")));

                Subject subject = new Subject();
                subject.setId(Long.parseLong(request.getParameter("subject")));

                group.setTeacher(teacher);
                group.setSubject(subject);
                groupService.saveGroup(group);

            } else if (action.equalsIgnoreCase("allGroups")) {
                request.setAttribute("subjectList", subjectService.getAllSubjects());
                request.setAttribute("teacherList", teacherService.getAllTeachersForComboBox());
                request.setAttribute("groupList", groupService.getAllGroups());
                address = "/groupPage.jsp";
            } else if (action.equalsIgnoreCase("infoGroup")) {
                Long id = Long.valueOf(request.getParameter("id"));
                Group group = groupService.getGroupInfoById(id);
                request.setAttribute("group", group);
                request.setAttribute("subjects", subjectService.getAllSubjectsWithout(group.getSubject().getId()));
                request.setAttribute("teachers", teacherService.getAllTeachersForComboBoxWithout(group.getTeacher().getId()));
                request.setAttribute("students", studentService.getStudentsByGroupid(id));
                address = "/groupInfo.jsp";
            } else if (action.equalsIgnoreCase("updateGroup")) {
                Long id = Long.valueOf(request.getParameter("id"));
                Group group = new Group();
                group.setGroupName(request.getParameter("groupName"));
                group.setId(id);
                Teacher teacher = new Teacher();
                teacher.setId(Long.parseLong(request.getParameter("teacher")));

                Subject subject = new Subject();
                subject.setId(Long.parseLong(request.getParameter("subject")));

                group.setTeacher(teacher);
                group.setSubject(subject);
                groupService.updateGroup(group);
                response.sendRedirect("http://localhost:8080/smawebapp_war_exploded/groups?action=infoGroup&id=" + group.getId());
            } else if (action.equalsIgnoreCase("deletegroup")) {
                Long id = Long.valueOf(request.getParameter("id"));
                groupService.softDeleteGroup(id);
                response.sendRedirect("http://localhost:8080/smawebapp_war_exploded/groups?action=allGroups");
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher(address);
            dispatcher.forward(request, response);

        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
