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

  <!-- Toggler / collapsible Button -->
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
  </button>

  <!-- Navbar links -->
  <div class="collapse navbar-collapse" id="collapsibleNavbar">
    <ul class="navbar-nav">
    <li class="nav-item">
      <a class="nav-link" href="/home">Home</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="mailto:info@yaadle.com">Contact</a>
    </li>
 </ul>
 <div class="nav nav-right">
 <c:url value="/logout" var="logoutUrl" />
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
  </div>
 </div>
 </div>
 </nav>
 <div class="row " style="margin-top:25px;">
  <div class="col-md-4" style="margin-left:220px;">
    <div class="card card-inverse" style="border-color: #333;">
      <div class="card-block">
        <h4 class="card-title text-center" style="margin-left:10px;margin-top:10px;">Registration Confirmed!</h4>
        <hr>
       <form id="" action="/" method="GET">
        <span>
          Hi <%=request.getSession().getAttribute("email") %> You have successfully confirmed your registration. 
             <button class="btn btn-success" type="submit" style="margin-left:10px !important;">Login</button>
             <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                </span> 
         </form>	

      </div>
     </div>
   </div>
 </div>
</body>
	</html>