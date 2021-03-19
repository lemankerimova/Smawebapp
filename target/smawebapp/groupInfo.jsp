<%@ page import="model.Group" %>
<%@ page import="model.Teacher" %>
<%@ page import="model.Subject" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Student" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%
    Group group = (Group) request.getAttribute("group");
    List<Teacher> teachers = (List<Teacher>) request.getAttribute("teachers");
    List<Subject> subjects = (List<Subject>) request.getAttribute("subjects");
    List<Student> students = (List<Student>) request.getAttribute("students");

%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Java</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js"
            integrity="sha384-KsvD1yqQ1/1+IA7gi3P0tyJcT3vR+NdBTt13hSJ2lnve8agRGXTTyNaBYmCR/Nwi"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js"
            integrity="sha384-nsg8ua9HAw1y0W1btsyWgBklPnCUAFLuTMS2G72MMONqmOymq585AcH49TLBQObG"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">SMApp</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0 myNavBar">
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="#">Home Page</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Students</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Teachers</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Subjects</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Groups</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Payments</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="container-fluid">
    <div style="margin-top: 1%" class="accordion accordion-flush" id="accordionFlushExample">
        <div class="accordion-item">
            <h2 class="accordion-header" id="flush-headingOne">
                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                        data-bs-target="#flush-collapseOne" aria-expanded="false"
                        aria-controls="flush-collapseOne">
                    Update Group
                </button>
            </h2>
            <div id="flush-collapseOne" class="accordion-collapse collapse"
                 aria-labelledby="flush-headingOne"
                 data-bs-parent="#accordionFlushExample">
                <div class="accordion-body">
                    <form method="post"
                          action="/smawebapp_war_exploded/groups?action=updateGroup&id=<%=group.getId()%>"
                          class="row gx-3 gy-2 align-items-center">
                        <div class="col-sm-3">
                            <label class="visually-hidden" for="specificSizeInputName">Name</label>
                            <input name="groupName" type="text" class="form-control"
                                   id="specificSizeInputName"
                                   value="<%=group.getGroupName()%>" placeholder="Group Name">
                        </div>

                        <div class="col-sm-3">
                            <label class="visually-hidden" for="specificSizeSelect">Preference</label>
                            <select class="form-select" id="specificSizeSelect">
                                <option value="<%=group.getTeacher().getId()%>"
                                        selected><%=group.getTeacher().getName()%>
                                    <%=group.getTeacher().getSurname()%>
                                </option>
                                <% for (Teacher teacher : teachers) {%>
                                <option value="<%=teacher.getId()%>">
                                    <%=teacher.getName()%> <%=teacher.getSurname()%>
                                </option>
                                <%}%>
                            </select>
                        </div>
                        <div class="col-sm-3">
                            <label class="visually-hidden" for="specificSizeSelect">Preference</label>
                            <select class="form-select" id="specificSizeSelect">
                                <option value="<%=group.getSubject().getId()%>"
                                        selected><%=group.getSubject().getSubjectName()%>
                                </option>
                                <% for (Subject subject : subjects) {%>
                                <option value="<%=subject.getId()%>">
                                    <%=subject.getSubjectName()%>
                                </option>
                                <%}%>
                            </select>
                        </div>
                        <div class="col-auto">
                            <button type="submit" class="btn btn-success">Save</button>
                        </div>
                </div>
            </div>
        </div>
    </div>


    <table style="margin-top: 1%" class="table table-striped table-hover">
        <thead>
        <tr>
            <th scope="col">Ad/th>
            <th scope="col">Soyad</th>
            <th scope="col">Telefon</th>
            <th scope="col">Email</th>
            <th scope="col">Info</th>
            <th scope="col">Delete</th>

        </tr>
        </thead>
        <tbody>
        <tr>
            <th scope="row">1</th>
            <td>>qwerty</td>
            <td>sdfg</td>
            <td> lsla  </td>
            <td> la</td>
            <td>
                <button type="button" class="btn btn-info">Info</button>
            </td>
            <td>
                <button type="button" class="btn btn-danger">Delete</button>
            </td>

        </tr>
        </tbody>
    </table>
</div>
</body>
</html>