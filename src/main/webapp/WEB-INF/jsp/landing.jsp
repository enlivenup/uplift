<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Yaadle</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
  
  <style>
<script>
  FB.getLoginStatus(function(response) {
    statusChangeCallback(response);
});
</script>
body {
background-image: url('http://localhost/images/idea.jpeg');
background-repeat: no-repeat;
width: 100%;
background-size:100%;
opactity:;
}
.input-group {
 padding-right:40px;
  position:relative;
}
#fb.btn-outline-primary:hover{
background-color:#3b5998 !important;
color: #fff !important;
border-color: #fff !important;
}
#ln.btn-outline-primary:hover{
background-color:#0077B5 !important;
color: #fff !important;
border-color: #fff !important;
}
#tw.btn-outline-primary:hover{
background-color:#1dcaff !important;
color: #fff !important;
border-color: #fff !important;
}
</style>
  </head>
<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
<div class="container">
  <!-- Brand -->
  <a class="navbar-brand" href="#">Yaadle</a>

  <!-- Toggler/collapsibe Button -->
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
  </button>

  <!-- Navbar links -->
  <div class="collapse navbar-collapse" id="collapsibleNavbar">
    <ul class="navbar-nav">
    <li class="nav-item">
      <a class="nav-link" href="#">Home</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="mailto:info@yaadle.com">Contact</a>
    </li>
        <li class="nav-item">
      <a class="nav-link" href="mailto:info@yaadle.com"></a>
    </li>
      <li>
        <li class="nav-item">
      <span>
        <a class="nav-link">
        <img src="/images/gicons/glyphicons-4-user.png">
        <span class="caret green"></span>
        <%=request.getUserPrincipal().getName() %>
        </a></span>
    </li>
 </ul>
 <div class="nav nav-right">
 <c:url value="/logout" var="logoutUrl" />
  <form id="logout" action="${logoutUrl}" method=POST>
  <button class="btn btn-primary" type="submit" style="margin-left:10px !important;">Logout</button>
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
  </form></div>
 </div>
 </div>
 </nav>
  <h1>LANDED HOME</h1>
</body>
	</html>