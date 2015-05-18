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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.meera.db.service.http.EmployeeServiceSoap}.
 *
 * @author E5410
 * @see com.meera.db.service.http.EmployeeServiceSoap
 * @generated
 */
public class EmployeeSoap implements Serializable {
	public static EmployeeSoap toSoapModel(Employee model) {
		EmployeeSoap soapModel = new EmployeeSoap();

		soapModel.setEmplyeeId(model.getEmplyeeId());
		soapModel.setEmplyeeName(model.getEmplyeeName());
		soapModel.setEmployeeDesignation(model.getEmployeeDesignation());

		return soapModel;
	}

	public static EmployeeSoap[] toSoapModels(Employee[] models) {
		EmployeeSoap[] soapModels = new EmployeeSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static EmployeeSoap[][] toSoapModels(Employee[][] models) {
		EmployeeSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new EmployeeSoap[models.length][models[0].length];
		}
		else {
			soapModels = new EmployeeSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static EmployeeSoap[] toSoapModels(List<Employee> models) {
		List<EmployeeSoap> soapModels = new ArrayList<EmployeeSoap>(models.size());

		for (Employee model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new EmployeeSoap[soapModels.size()]);
	}

	public EmployeeSoap() {
	}

	public long getPrimaryKey() {
		return _emplyeeId;
	}

	public void setPrimaryKey(long pk) {
		setEmplyeeId(pk);
	}

	public long getEmplyeeId() {
		return _emplyeeId;
	}

	public void setEmplyeeId(long emplyeeId) {
		_emplyeeId = emplyeeId;
	}

	public String getEmplyeeName() {
		return _emplyeeName;
	}

	public void setEmplyeeName(String emplyeeName) {
		_emplyeeName = emplyeeName;
	}

	public String getEmployeeDesignation() {
		return _employeeDesignation;
	}

	public void setEmployeeDesignation(String employeeDesignation) {
		_employeeDesignation = employeeDesignation;
	}

	private long _emplyeeId;
	private String _emplyeeName;
	private String _employeeDesignation;
}