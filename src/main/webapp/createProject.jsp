<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Show all projects</title>
    </head>
    <body>
        <h1>Create project</h1>
        <form:form >
            Name: <form:input path="name"/><br>
            Description: <form:input path="description"/><br>
            TempCreator: <form:input path="creator"/><br>
        <form:form>
    </body>
</html>