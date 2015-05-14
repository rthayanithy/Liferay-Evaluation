<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<portlet:defineObjects />
<liferay-theme:defineObjects />
<h1>Liferay Service Builder/ Student CRUD Operations</h1>
<portlet:renderURL var="addStudent">
<portlet:param name="mvcPath" value="/html/jsps/add_student.jsp"/>
</portlet:renderURL>
<portlet:renderURL var="updateStudent">
<portlet:param name="mvcPath" value="/html/jsps/update_student.jsp"/>
</portlet:renderURL>
<portlet:renderURL var="dislayStudent">
<portlet:param name="mvcPath" value="/html/jsps/display_student.jsp"/>
</portlet:renderURL>
<portlet:renderURL var="deleteStudent">
<portlet:param name="mvcPath" value="/html/jsps/delete_student.jsp"/>
</portlet:renderURL>
<br/>
<a href="<%=addStudent.toString()%>">Add Student</a><br/>
<a href="<%=updateStudent.toString()%>">Update Student</a><br/>
<a href="<%=deleteStudent.toString()%>">Delete Student</a><br/>
<a href="<%=dislayStudent.toString()%>">Display Student</a><br/>
