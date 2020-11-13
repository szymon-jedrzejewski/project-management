<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Show all projects</title>
    </head>
    <body>
        <h1>Projects:</h1>
        <a href="createProject.jsp?action=ShowForm&name=CreateProjectForm">Create project</a><br><br>

        <c:forEach var="project" items="${projects}">
            <a href="index.html?action=ShowProject&id=${project.id}">${project.name}</a>
            <a href="index.html?action=EditProject&id=${project.id}">| Edit Project</a>
            <a href="index.html?action=DeleteProject&id=${project.id}">| Delete Project<br>
        </c:forEach>

    </body>
</html>