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
  <link rel="icon" type="/images" href="favicon.ico" />
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
  
  <style>
body {
background-image: url('http://localhost/images/coach.jpeg');
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

 </ul>
  <form class="form-inline" action="<c:url value='/home' />" style="margin-left:40px;" method=POST>
    <div class="input-group">
      <span class="input-group-addon" ><img src="/images/gicons/glyphicons-4-user.png"></span>
      <input type="text" class="form-control " placeholder="Email" style="margin-right:10px !important;" name="username" required>
      <span class="input-group-addon"> <img src="/images/gicons/glyphicons-204-lock.png"></span>
      <input type="password" class="form-control" placeholder="Password"  name="password" required>
      <button class="btn btn-success" type="submit" style="margin-left:10px !important;">Login</button>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </div> 
  </form>
  <form class="form-social" action="/forgotpassword" method=POST style="margin-left:20px !important;">
      <a href="/forgotpassword" style="color:white;">Forgot Password?</a>
  </form> 
  </div>
 </div>
</nav>
<!-- Test Form -->
<div class="row " style="margin-top:25px;">
  <div class="col-md-4" style="margin-left:220px;">
    <div class="card card-inverse" style="border-color: #333;">
      <div class="card-block">
        <h4 class="card-title text-center" style="margin-left:10px;margin-top:10px;">SignUp!</h4>
        <hr>
        <form class="form-group" action="/register" method=POST>
				<div class="input-group" style="margin-left:25px;">
  				
  				<span class="input-group-addon" id="basic-addon0" style="width:150px;">Email Address</span>
  				
  				<input type="text" class="form-control" id="email" name="email" aria-describedby="basic-addon3" placeholder="Username / Email" required>
			    
			    </div>
			    <div class="input-group" style="margin-top:25px;margin-left:25px;">
  				<span class="input-group-addon" id="basic-addon1" style="width:150px;">Display Name</span>
  				<input type="text" class="form-control" id="displayname" name="displayname" placeholder="Full Name" required>
			    </div>
			    <div class="input-group" style="margin-top:25px;margin-left:25px;">
  				<span class="input-group-addon" id="basic-addon2" style="width:150px;">Password</span>
  				<input type="password" class="form-control" id="password" name="password" placeholder="Password" required>
			    </div>
			     <div class="input-group" style="margin-top:25px;margin-left:25px;" >
  				<span class="input-group-addon" id="basic-addon3" style="width:150px;">Confirm Password</span>
  				<input type="password" class="form-control" name="confirmpassword" id="confirmpassword"  placeholder="Confirm Password" required>
			    </div>
   		        <div class="input-group" style="margin-top:25px;margin-left:25px;" >
			    <label style="margin-left:25px;margin-top:"><input type="checkbox" class="form-check" name="remember" id="remember"> Remember me</label>
			    <input type="submit" class="btn btn-success" style="margin-left:25px;width:220px;" value="Submit" onClick="location.href='/home"> 
                </div>
                <hr>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        </form>
        
        <div id="socialType" class="row" style="margin-top:25px;margin-left:5px;">
        <form action="/signup/facebook" method="POST">
             <button id="fb" type="submit" class="btn btn-outline-primary" style="margin-left:25px;color:#3b5998;">SignUp with <strong>f</strong>acebook</button>
             <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        </form>	
        <form action="/signup/linkedin" method="POST">
             <button id="ln" type="submit" class="btn btn-outline-primary" style="margin-left:25px;color:#0077B5;">SignUp with linked<strong>in</strong></button>
             <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
         </form>	
         <form action="/signup/twitter" method="POST">
             <button id="tw" type="submit" class="btn btn-outline-primary" style="margin-left:25px;color:#1dcaff;">SignUp with <strong>twitter</strong></button>
             <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
         </form>	 
        </div>	
        
        <br>
        <br>
      </div>
    </div>
  </div>
</div>
</body>
	</html>
	