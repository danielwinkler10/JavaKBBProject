<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="adapter.BuildAuto" %>

<html>
<head>
    <title></title>
</head>
<body>

</body>
</html>


<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>getPrice</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.8.0/css/bulma.min.css">
    <script defer src="https://use.fontawesome.com/releases/v5.3.1/js/all.js"></script>
</head>

<body>

<nav class="navbar has-background-primary" role="navigation" aria-label="main navigation">
    <div id="navbar" class="navbar-menu">
        <div class="navbar-start">
            <a class="navbar-item" href="upload.jsp">
                <i class="fas fa-upload"></i>

                Upload
            </a>

            <a class="navbar-item" href="select.jsp">
                <i class="fas fa-download"></i>
                Get Price
            </a>

        </div>
    </div>
    </div>
</nav>


<div align="center" style="margin-top: 50px;">
    Available models -
    <%
        BuildAuto build = new BuildAuto();
        for (int i=0; i<build.getUploadedNames().size();i++){
            %>
            <%
            out.println(build.getUploadedNames().get(i));
        }
    %>
    <form action="select">
        Enter the car name(without the .dat):  <input type="text" name="carName" size="20px"> <br><br>
        <input type="submit" value="submit">
    </form>

</div>

</body>

</html>