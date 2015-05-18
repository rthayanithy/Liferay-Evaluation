/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.meera.db.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import com.meera.db.service.ClpSerializer;
import com.meera.db.service.EmployeeLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

/**
 * @author E5410
 */
public class EmployeeClp extends BaseModelImpl<Employee> implements Employee {
	public EmployeeClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return Employee.class;
	}

	@Override
	public String getModelClassName() {
		return Employee.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _emplyeeId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setEmplyeeId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _emplyeeId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("emplyeeId", getEmplyeeId());
		attributes.put("emplyeeName", getEmplyeeName());
		attributes.put("employeeDesignation", getEmployeeDesignation());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long emplyeeId = (Long)attributes.get("emplyeeId");

		if (emplyeeId != null) {
			setEmplyeeId(emplyeeId);
		}

		String emplyeeName = (String)attributes.get("emplyeeName");

		if (emplyeeName != null) {
			setEmplyeeName(emplyeeName);
		}

		String employeeDesignation = (String)attributes.get(
				"employeeDesignation");

		if (employeeDesignation != null) {
			setEmployeeDesignation(employeeDesignation);
		}
	}

	@Override
	public long getEmplyeeId() {
		return _emplyeeId;
	}

	@Override
	public void setEmplyeeId(long emplyeeId) {
		_emplyeeId = emplyeeId;

		if (_employeeRemoteModel != null) {
			try {
				Class<?> clazz = _employeeRemoteModel.getClass();

				Method method = clazz.getMethod("setEmplyeeId", long.class);

				method.invoke(_employeeRemoteModel, emplyeeId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getEmplyeeName() {
		return _emplyeeName;
	}

	@Override
	public void setEmplyeeName(String emplyeeName) {
		_emplyeeName = emplyeeName;

		if (_employeeRemoteModel != null) {
			try {
				Class<?> clazz = _employeeRemoteModel.getClass();

				Method method = clazz.getMethod("setEmplyeeName", String.class);

				method.invoke(_employeeRemoteModel, emplyeeName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getEmployeeDesignation() {
		return _employeeDesignation;
	}

	@Override
	public void setEmployeeDesignation(String employeeDesignation) {
		_employeeDesignation = employeeDesignation;

		if (_employeeRemoteModel != null) {
			try {
				Class<?> clazz = _employeeRemoteModel.getClass();

				Method method = clazz.getMethod("setEmployeeDesignation",
						String.class);

				method.invoke(_employeeRemoteModel, employeeDesignation);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getEmployeeRemoteModel() {
		return _employeeRemoteModel;
	}

	public void setEmployeeRemoteModel(BaseModel<?> employeeRemoteModel) {
		_employeeRemoteModel = employeeRemoteModel;
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

		Class<?> remoteModelClass = _employeeRemoteModel.getClass();

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

		Object returnValue = method.invoke(_employeeRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			EmployeeLocalServiceUtil.addEmployee(this);
		}
		else {
			EmployeeLocalServiceUtil.updateEmployee(this);
		}
	}

	@Override
	public Employee toEscapedModel() {
		return (Employee)ProxyUtil.newProxyInstance(Employee.class.getClassLoader(),
			new Class[] { Employee.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		EmployeeClp clone = new EmployeeClp();

		clone.setEmplyeeId(getEmplyeeId());
		clone.setEmplyeeName(getEmplyeeName());
		clone.setEmployeeDesignation(getEmployeeDesignation());

		return clone;
	}

	@Override
	public int compareTo(Employee employee) {
		int value = 0;

		if (getEmplyeeId() < employee.getEmplyeeId()) {
			value = -1;
		}
		else if (getEmplyeeId() > employee.getEmplyeeId()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof EmployeeClp)) {
			return false;
		}

		EmployeeClp employee = (EmployeeClp)obj;

		long primaryKey = employee.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{emplyeeId=");
		sb.append(getEmplyeeId());
		sb.append(", emplyeeName=");
		sb.append(getEmplyeeName());
		sb.append(", employeeDesignation=");
		sb.append(getEmployeeDesignation());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(13);

		sb.append("<model><model-name>");
		sb.append("com.meera.db.model.Employee");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>emplyeeId</column-name><column-value><![CDATA[");
		sb.append(getEmplyeeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>emplyeeName</column-name><column-value><![CDATA[");
		sb.append(getEmplyeeName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>employeeDesignation</column-name><column-value><![CDATA[");
		sb.append(getEmployeeDesignation());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _emplyeeId;
	private String _emplyeeName;
	private String _employeeDesignation;
	private BaseModel<?> _employeeRemoteModel;
}