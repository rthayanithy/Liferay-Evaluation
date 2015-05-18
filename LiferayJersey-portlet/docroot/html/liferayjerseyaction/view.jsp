<%--
/**
* Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
*
* This library is free software; you can redistribute it and/or modify it under
* the terms of the GNU Lesser General Public License as published by the Free
* Software Foundation; either version 2.1 of the License, or (at your option)
* any later version.
*
* This library is distributed in the hope that it will be useful, but WITHOUT
* ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
* FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
* details.
*/
--%>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@include file="init.jsp"%>
<style>
table{
background:geray;
}
table th{
padding:15px 15px 15px 15px;
}
table td{
padding:15px 15px 15px 15px;
}
</style>
<script src="/html/js/jquery/jquery.js" language="javascript" type="text/javascript"></script>
<portlet:actionURL var="liferayJerseyActionURL" windowState="NORMAL">
	<portlet:param name="javax.portlet.action" value="sendRestRequest" />
</portlet:actionURL>
<h1>Liferay Jersey Restful web services</h1>
<ul>
<aui:a href="#" id="addPersonLink"><li>Add Person</li></aui:a>
<aui:a href="#" id="getPersonLink"><li>Get Person</li></aui:a>
</ul>
<div class="addPersonDiv" style="display:none;">
<aui:form action="<%=liferayJerseyActionURL.toString() %>" method="POST" name="liferayJerseyForm" id="liferayJerseyForm">
<fieldset>
<legend>Add Person Form</legend>
	<aui:layout>
		<aui:column>
			<aui:input name="personName" id="personName" label="Person Name:"></aui:input>
		</aui:column>
	</aui:layout>
	<aui:layout>
		<aui:column>
			<aui:input name="personAddress" id="personAddress" label="Person Address:"></aui:input>
		</aui:column>
	</aui:layout>
	<aui:layout>
		<aui:column>
		<div class="successMessage" style='display:none;margin-top:20px;margin-bottom:20px;color:green;'><b>The following person added successfuly by rest web servide call</b></div>
			<div class="createdPesron"></div>
		</aui:column>
	</aui:layout>
	<aui:button-row>
	
	<aui:button id="addperson" name="addperson" value="Add Person" label="false"></aui:button>
	</aui:button-row>
</fieldset>
</aui:form>
</div>
<div class="getPersonDiv" style="display:none;">
<aui:form action="<%=liferayJerseyActionURL.toString() %>" method="POST" name="liferayJerseyGetForm" id="liferayJerseyGetForm">
<fieldset>
<legend>Get Person Form</legend>
	<aui:layout>
		<aui:column>
			<aui:input name="personId" id="personId" label="Person Id:"></aui:input>
		</aui:column>
	</aui:layout>
	<aui:layout>
		<aui:column>
			<div class="getPesronInfo"></div>
		</aui:column>
	</aui:layout>
	<aui:button-row>
	<aui:button id="getperson" name="getperson" value="Get Person" label="false"></aui:button>
	</aui:button-row>
</fieldset>
</aui:form>

</div>
<script>
jQuery('#<portlet:namespace />addperson').click(function(){
	var baseurl = 'http://localhost:8080/LiferayJersey-portlet/rest/person/addperson';
	var personName= jQuery('#<portlet:namespace />personName').val();
	var personAddress = jQuery('#<portlet:namespace />personAddress').val();
	var restURL=baseurl+"/name/"+personName+"/address/"+personAddress;
	jQuery.ajax({
	    type :"POST",
	    url :restURL,
	    dataType :"json",
	    data: {},
	success:function(msg) {
		var table="<table border='1'><tr><th>Person Id</th><th>Person Name</th><th>Person Address</th></tr>"
		+"<tr><td>"+msg.personId+"</td><th>"+msg.personName+"</td><td>"+msg.PersonAddress+"</td></tr></table>";
		jQuery('div.createdPesron').html(table);
		jQuery('.successMessage').show();
		jQuery("#<portlet:namespace />liferayJerseyForm").reset();
			 },
	error : function() {
		
	       
	}
	}); 
	});
jQuery('#<portlet:namespace />getperson').click(function(){
	
	var baseurl = 'http://localhost:8080/LiferayJersey-portlet/rest/person/getperson';
	var personId= jQuery('#<portlet:namespace />personId').val();
	var restURL1=baseurl+"/id/"+personId;
	jQuery.ajax({
	    type :"GET",
	    url :restURL1,
	    dataType :"json",
	    data: {},
	success:function(msg1) {
		var table="<table border='1'><tr><th>Person Id</th><th>Person Name</th><th>Person Address</th></tr>"
		+"<tr><td>"+msg1.personId+"</td><th>"+msg1.personName+"</td><td>"+msg1.PersonAddress+"</td></tr></table>";
		jQuery('div.getPesronInfo').html(table);
			 },
	error : function() {
		
	       
	}
	}); 
	});
jQuery('#<portlet:namespace />addPersonLink').click(function(){
	jQuery('.getPersonDiv').hide();
	jQuery('.addPersonDiv').show();
});

jQuery('#<portlet:namespace />getPersonLink').click(function(){
	jQuery('.addPersonDiv').hide();
	jQuery('.getPersonDiv').show();
});

</script>

