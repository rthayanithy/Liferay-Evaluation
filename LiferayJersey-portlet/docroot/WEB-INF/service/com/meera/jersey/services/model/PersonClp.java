/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.meera.jersey.services.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import com.meera.jersey.services.service.ClpSerializer;
import com.meera.jersey.services.service.PersonLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

/**
 * @author meera
 */
public class PersonClp extends BaseModelImpl<Person> implements Person {
	public PersonClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return Person.class;
	}

	@Override
	public String getModelClassName() {
		return Person.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _personId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setPersonId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _personId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("personId", getPersonId());
		attributes.put("personName", getPersonName());
		attributes.put("personAddress", getPersonAddress());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long personId = (Long)attributes.get("personId");

		if (personId != null) {
			setPersonId(personId);
		}

		String personName = (String)attributes.get("personName");

		if (personName != null) {
			setPersonName(personName);
		}

		String personAddress = (String)attributes.get("personAddress");

		if (personAddress != null) {
			setPersonAddress(personAddress);
		}
	}

	@Override
	public long getPersonId() {
		return _personId;
	}

	@Override
	public void setPersonId(long personId) {
		_personId = personId;

		if (_personRemoteModel != null) {
			try {
				Class<?> clazz = _personRemoteModel.getClass();

				Method method = clazz.getMethod("setPersonId", long.class);

				method.invoke(_personRemoteModel, personId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getPersonName() {
		return _personName;
	}

	@Override
	public void setPersonName(String personName) {
		_personName = personName;

		if (_personRemoteModel != null) {
			try {
				Class<?> clazz = _personRemoteModel.getClass();

				Method method = clazz.getMethod("setPersonName", String.class);

				method.invoke(_personRemoteModel, personName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getPersonAddress() {
		return _personAddress;
	}

	@Override
	public void setPersonAddress(String personAddress) {
		_personAddress = personAddress;

		if (_personRemoteModel != null) {
			try {
				Class<?> clazz = _personRemoteModel.getClass();

				Method method = clazz.getMethod("setPersonAddress", String.class);

				method.invoke(_personRemoteModel, personAddress);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getPersonRemoteModel() {
		return _personRemoteModel;
	}

	public void setPersonRemoteModel(BaseModel<?> personRemoteModel) {
		_personRemoteModel = personRemoteModel;
	}

	public Object invokeOnRemoteModel(String methodName,
		Class<?>[] parameterTypes, Object[] parameterValues)
		throws Exception {
		Object[] remoteParameterValues = new Object[parameterValues.length];

		for (int i = 0; i < parameterValues.length; i++) {
			if (parameterValues[i] != null) {
				remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
			}
		}

		Class<?> remoteModelClass = _personRemoteModel.getClass();

		ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

		Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

		for (int i = 0; i < parameterTypes.length; i++) {
			if (parameterTypes[i].isPrimitive()) {
				remoteParameterTypes[i] = parameterTypes[i];
			}
			else {
				String parameterTypeName = parameterTypes[i].getName();

				remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
			}
		}

		Method method = remoteModelClass.getMethod(methodName,
				remoteParameterTypes);

		Object returnValue = method.invoke(_personRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			PersonLocalServiceUtil.addPerson(this);
		}
		else {
			PersonLocalServiceUtil.updatePerson(this);
		}
	}

	@Override
	public Person toEscapedModel() {
		return (Person)ProxyUtil.newProxyInstance(Person.class.getClassLoader(),
			new Class[] { Person.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		PersonClp clone = new PersonClp();

		clone.setPersonId(getPersonId());
		clone.setPersonName(getPersonName());
		clone.setPersonAddress(getPersonAddress());

		return clone;
	}

	@Override
	public int compareTo(Person person) {
		long primaryKey = person.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PersonClp)) {
			return false;
		}

		PersonClp person = (PersonClp)obj;

		long primaryKey = person.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	public Class<?> getClpSerializerClass() {
		return _clpSerializerClass;
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{personId=");
		sb.append(getPersonId());
		sb.append(", personName=");
		sb.append(getPersonName());
		sb.append(", personAddress=");
		sb.append(getPersonAddress());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(13);

		sb.append("<model><model-name>");
		sb.append("com.meera.jersey.services.model.Person");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>personId</column-name><column-value><![CDATA[");
		sb.append(getPersonId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>personName</column-name><column-value><![CDATA[");
		sb.append(getPersonName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>personAddress</column-name><column-value><![CDATA[");
		sb.append(getPersonAddress());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _personId;
	private String _personName;
	private String _personAddress;
	private BaseModel<?> _personRemoteModel;
	private Class<?> _clpSerializerClass = com.meera.jersey.services.service.ClpSerializer.class;
}