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

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Person}.
 * </p>
 *
 * @author meera
 * @see Person
 * @generated
 */
public class PersonWrapper implements Person, ModelWrapper<Person> {
	public PersonWrapper(Person person) {
		_person = person;
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

	/**
	* Returns the primary key of this person.
	*
	* @return the primary key of this person
	*/
	@Override
	public long getPrimaryKey() {
		return _person.getPrimaryKey();
	}

	/**
	* Sets the primary key of this person.
	*
	* @param primaryKey the primary key of this person
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_person.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the person ID of this person.
	*
	* @return the person ID of this person
	*/
	@Override
	public long getPersonId() {
		return _person.getPersonId();
	}

	/**
	* Sets the person ID of this person.
	*
	* @param personId the person ID of this person
	*/
	@Override
	public void setPersonId(long personId) {
		_person.setPersonId(personId);
	}

	/**
	* Returns the person name of this person.
	*
	* @return the person name of this person
	*/
	@Override
	public java.lang.String getPersonName() {
		return _person.getPersonName();
	}

	/**
	* Sets the person name of this person.
	*
	* @param personName the person name of this person
	*/
	@Override
	public void setPersonName(java.lang.String personName) {
		_person.setPersonName(personName);
	}

	/**
	* Returns the person address of this person.
	*
	* @return the person address of this person
	*/
	@Override
	public java.lang.String getPersonAddress() {
		return _person.getPersonAddress();
	}

	/**
	* Sets the person address of this person.
	*
	* @param personAddress the person address of this person
	*/
	@Override
	public void setPersonAddress(java.lang.String personAddress) {
		_person.setPersonAddress(personAddress);
	}

	@Override
	public boolean isNew() {
		return _person.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_person.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _person.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_person.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _person.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _person.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_person.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _person.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_person.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_person.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_person.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new PersonWrapper((Person)_person.clone());
	}

	@Override
	public int compareTo(com.meera.jersey.services.model.Person person) {
		return _person.compareTo(person);
	}

	@Override
	public int hashCode() {
		return _person.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.meera.jersey.services.model.Person> toCacheModel() {
		return _person.toCacheModel();
	}

	@Override
	public com.meera.jersey.services.model.Person toEscapedModel() {
		return new PersonWrapper(_person.toEscapedModel());
	}

	@Override
	public com.meera.jersey.services.model.Person toUnescapedModel() {
		return new PersonWrapper(_person.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _person.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _person.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_person.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PersonWrapper)) {
			return false;
		}

		PersonWrapper personWrapper = (PersonWrapper)obj;

		if (Validator.equals(_person, personWrapper._person)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public Person getWrappedPerson() {
		return _person;
	}

	@Override
	public Person getWrappedModel() {
		return _person;
	}

	@Override
	public void resetOriginalValues() {
		_person.resetOriginalValues();
	}

	private Person _person;
}