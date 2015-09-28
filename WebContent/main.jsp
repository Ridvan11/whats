<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>What's on my Close ?</title>
  <link href="css/bootstrap.min.css" rel="stylesheet" media="screen"> 
</head>
<body>
<div align="center" style="margin-top=50px";>
		<form action="main">
			<br>Town:<input type="text" name="town" size=20px>
			<br>City:<input type="text" name="city" size=20px>
			<br>Country:<input type="text" name="country" size=20px>
			<br><input class="btn btn-primary" type="submit" value="submit">
			
			
			
		</form>
	</div>
	<div>
	<% 
	String weather=(String)request.getAttribute("weather");
	out.print("<br>" + weather);
	%>
	
	</div>
	<div>
		<%String resul=(String)request.getAttribute("resul");
		
		out.print("<strong><br>Restaurant<br></strong>: " + resul);
		
		%>
	</div>
	<div>
		<%String resuClub=(String)request.getAttribute("resuClub");
		
		out.print("<strong><br>Hospital's<br></strong>: " + resuClub);
		
		%>
		
	</div>
	

</body>
</html>