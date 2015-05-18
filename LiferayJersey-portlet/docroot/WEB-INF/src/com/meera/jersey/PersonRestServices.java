package com.meera.jersey;

import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.apache.log4j.Logger;
// The Java class will be hosted at the URI path "/persons"

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.meera.jersey.services.model.Person;
import com.meera.jersey.services.model.impl.PersonImpl;
import com.meera.jersey.services.service.PersonLocalServiceUtil;
@Path("/person")
public class PersonRestServices{
	private static Log log = LogFactoryUtil.getLog(PersonRestServices.class);
	@POST
	@Path("addperson/name/{name}/address/{address}")
	@Produces("application/json")
	public String addPerson(@PathParam("name") String name,
			@PathParam("address") String address) throws SystemException {
		log.info("name: " + name);
		log.info("age : " + address);

		Person person = new PersonImpl();
		person.setPersonName(name);
		person.setPersonAddress(address);
		person=PersonLocalServiceUtil.addPerson(person);
		log.info("1111111111111" + person.toString());
		StringBuffer jsonBuffer = new StringBuffer();
		jsonBuffer.append(getJsonPersonObject(person));
		
		log.info("2222222222222222" +getJsonPersonObject(person).toString());
		log.info("json string : " + jsonBuffer.toString());
		return jsonBuffer.toString();
	}
	@GET
	@Path("getperson/id/{id}")
	@Produces("application/json")
	public String getPerson(@PathParam("id") String id) throws SystemException, NumberFormatException, PortalException {
		log.info("name: " + id);
		Person personObejct=PersonLocalServiceUtil.getPerson(Long.valueOf(id));
		StringBuffer jsonBuffer = new StringBuffer();
		jsonBuffer.append(getJsonPersonObject(personObejct));
		return jsonBuffer.toString();
	}
	private Object getJsonPersonObject(Person person) {
		StringBuffer buffer = new StringBuffer("{\"personId\":\""+ person.getPersonId());
		buffer.append("\",\"personName\": \"").append(person.getPersonName());
		buffer.append("\",\"PersonAddress\": \"").append(person.getPersonAddress());
		buffer.append("\"}");
		return buffer.toString();
	}
}
