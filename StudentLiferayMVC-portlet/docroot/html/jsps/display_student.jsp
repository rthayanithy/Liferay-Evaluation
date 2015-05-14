<%@page import="com.liferay.portal.kernel.servlet.SessionErrors"%>
<%@page import="com.liferay.portal.kernel.servlet.SessionMessages"%>
<%@page import="com.meera.dbservice.service.StudentLocalServiceUtil"%>
<%@page import="java.util.List"%>
<%@page import="com.meera.dbservice.model.Student"%>
<%@page import="com.liferay.portal.kernel.servlet.SessionErrors"%>
<%@page import="com.liferay.portal.kernel.servlet.SessionMessages"%>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<portlet:defineObjects />
<portlet:renderURL var="homeURL"></portlet:renderURL>
<portlet:actionURL var="getStudentActionURL" windowState="normal" name="getStudent">
<portlet:param name="cmd" value="VIEW"/>
</portlet:actionURL>
<h2>Display Student Information</h2>
<a href="<%=homeURL.toString() %>">Home</a><br/><br/>
<%
List<Student> studentList=StudentLocalServiceUtil.getStudents(0,StudentLocalServiceUtil.getStudentsCount());
Student selecteStudentObject=(Student)renderRequest.getAttribute("studentObject");
%>
<form action="<%=getStudentActionURL.toString()%>" name="studentForm"  method="POST">
<b>Select Student ID</b><br>
<select name="<portlet:namespace/>studentId" onchange="submitform(this.value);">
<option value="-1">--select--</option>
<%for(Student student:studentList){%>
  <option value="<%=student.getStudentId()%>" <%=selecteStudentObject!=null&&selecteStudentObject.getStudentId()==student.getStudentId()?"selected":""%>><%=student.getStudentId()%></option>
  <%} %>
</select><br>
<%if(selecteStudentObject!=null){%>
<h3>The following are the selected Student Information</h3><br/>
Student Name:<%=selecteStudentObject.getFirstName()+"&nbsp;"+selecteStudentObject.getLastName()%><br/>
Student Age: <%=selecteStudentObject.getStudentAge() %><br/>
Student Gender: <%=selecteStudentObject.getStudentGender()==1?"Male":"Famale"%><br/>
Address: <%=selecteStudentObject.getStudentAddress()%><br/>
<%}%>
</form>
<script>
function submitform(selectedValue)
{
  if(selectedValue!="-1"){
	  document.studentForm.submit();  
  }
	
}
</script>
