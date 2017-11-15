<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Download Editable Calendar</h1>
 
 <form:form action="pdf/calendar.pdf" modelAttribute="details">
 	<table>
 		<tr>
 			<td><form:label path="fromMonth">From Month</form:label></td>
 			<td><form:input path="fromMonth" value="May"></form:input></td>
 		</tr>
 		<tr>
 			<td><form:label path="ToMonth">To Month</form:label></td>
 			<td><form:input path="ToMonth" value="June"></form:input></td>
 		</tr>
 		<tr>
 			<td><form:label path="startYear">Start Year</form:label></td>
 			<td><form:input path="startYear" value="2017"></form:input></td>
 		</tr>
 		<tr>
 			<td><form:label path="endYear">End Year</form:label></td>
 			<td><form:input path="endYear" value="2017"></form:input></td>
 		</tr>
 		<tr>
 			<td>
 				<input type="submit" value="Submit"/>
 			</td>
 		</tr>
 	</table>
 
 </form:form>
 
</body>
</html>