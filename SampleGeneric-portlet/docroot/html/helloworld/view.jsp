<%@page import="java.util.Map"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<portlet:defineObjects />
<portlet:renderURL  var="addEmployee" windowState="normal">
<portlet:param name="jspPage" value="/html/helloworld/addEmployee.jsp"/>
</portlet:renderURL>
<portlet:renderURL  var="displayEmployees" windowState="normal">
<portlet:param name="jspPage" value="/html/helloworld/displayEmployees.jsp"/>
<portlet:param name="emplyeeName" value="Meera Prince"/>
</portlet:renderURL>
<h1>Welcome to Hello World Employee Portlet</h1>
<a href="<%=addEmployee.toString()%>">Add Employee</a><br/>
<a href="<%=displayEmployees.toString()%>">View All Employees</a><br/>

