<%@ page language = "java" contentType = "text/html; charset = ISO-8859-1"
   pageEncoding = "ISO-8859-1"%>
<%@ taglib prefix = "s" uri = "/struts-tags"%>
   <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">

<html>
   <head>
      <title>Login Form</title>
   </head>
   
   <body>
      <h2>Login </h2>
        <form action="login">
            Userame: <input type="text" name="username"/></br>
            Password: <input type="password" name="password"/></br>           
            <p/>
            <input type="submit" value="Login"/>
            <p/>
            <s:actionerror/>     
        </form>
   </body>
</html>