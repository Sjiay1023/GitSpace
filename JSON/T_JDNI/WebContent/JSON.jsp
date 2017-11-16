<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><%@page
	language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<html> 
<%@page import="java.sql.*" %>    
<%@page import="javax.naming.*" %>    
<%@page import="javax.sql.DataSource" %>  
<%@page import="jdbc.json.*"%>  
<head>
<title>JSON</title>
<meta http-equiv="Content-Type" >
</head>
<body>   
    This is my JSP page. <br>    
    JNDI ... <br>    
<%    
try {   
   String json = "{\"id\":\"8\",\"name\":\"python\",\"city\":\"南京\"}";  
   DbConn.GetConnection();
   out.println("111...");
   Wsi ws = new Wsi();
   ws.save_city(json);
   out.println("连接成功...");
} catch (Exception e) {    
    e.printStackTrace();    
}
%> 
</body>
</html>